package framework.zoker.com.libs.Photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumWrapper;

import java.util.ArrayList;

import framework.zoker.com.libs.Photo.album.GlideImageLoader;

/**
 *
 * Created by Zoker on 2017/4/21.
 */

public class PhotoUtil {
    private int toolbarColor=-1; // Toolbar 颜色，默认蓝色。

    private int statusBarColor=-1;  // StatusBar 颜色，默认蓝色。

    private int navigationBarColor=-1; // NavigationBar 颜色，默认黑色，建议使用默认。


    Activity activity;
    private PhotoUtil(Activity activity){
        this.activity=activity;
    }
    public static PhotoUtil with(Activity activity){
        return new PhotoUtil(activity);
    }

    //Album
    public void AlbumInit(){
        Album.initialize(new AlbumConfig.Build()
                .setImageLoader(new GlideImageLoader()) // Use glide loader.
                .build());
    }

    public void camera(int requestCode){
        Album.camera(activity)
                .requestCode(requestCode)
                // .imagePath() // 指定相机拍照的路径，建议非特殊情况不要指定.
                .start();
    }

    public void photo(int requestCode){
        photo(requestCode,1);
    }

    public void photo(int requestCode,int selectCount){
        photo(requestCode,selectCount,null);
    }


    public void photo(int requestCode, int selectCount, ArrayList<String> mImageList){
        AlbumWrapper wrapper=loadConfig(Album.album(activity)
                .requestCode(requestCode)) // 请求码，返回时onActivityResult()的第一个参数。
                .title("图库") // 配置title。
                .selectCount(selectCount) // 最多选择几张图片。
                .columnCount(4) // 相册展示列数，默认是2列。
                .camera(true); // 是否有拍照功能。
        if (mImageList!=null && !mImageList.isEmpty())
                wrapper.checkedList(mImageList); // 已经选择过得图片，相册会自动选中选过的图片，并计数。
                wrapper.start();
    }

    public AlbumWrapper loadConfig(AlbumWrapper wrapper){
        if (toolbarColor!=-1){
            wrapper.toolBarColor(toolbarColor);
        }
        if (statusBarColor!=-1){
            wrapper.statusBarColor(statusBarColor);
        }
        if (navigationBarColor!=-1){
            wrapper.navigationBarColor(navigationBarColor);
        }
        return wrapper;
    }

    public static ArrayList<String> albumParseResult(Intent data){
        return Album.parseResult(data);
    }

    public class AlbumBuilder {
        private int toolbarColor=-1; // Toolbar 颜色，默认蓝色。

        private int statusBarColor=-1;  // StatusBar 颜色，默认蓝色。

        private int navigationBarColor=-1; // NavigationBar 颜色，默认黑色，建议使用默认。

        public AlbumBuilder ToolbarColor(@ColorInt int toolbarColor){
            this.toolbarColor=toolbarColor;
            return this;
        }
        public AlbumBuilder StatusBarColor(@ColorInt int statusBarColor){
            this.statusBarColor=statusBarColor;
            return this;
        }
        public AlbumBuilder NavigationBarColor(@ColorInt int navigationBarColor){
            this.navigationBarColor=navigationBarColor;
            return this;
        }
    }
}
