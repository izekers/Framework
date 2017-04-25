package com.zoker.utils.photo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.ArrayMap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * Created by Administrator on 2017/4/20.
 */

public class Photographer {
   private Activity activity;
   private PhotoRecall recall;

   static Map<Activity,PhotoRecall> recallMap=new HashMap<>();

   Photographer(Activity activity){
        this.activity=activity;
   }

   public Photographer recall(PhotoRecall recall){
       this.recall=recall;
       if (recall!=null)
           recallMap.put(activity,recall);
       return this;
   }

    public void Thumbnail(){

    }

    /**
     * 拍摄缩略图
     * @param activity
     */
    public void shooting_thumbnail(Activity activity,PhotoRecall recall) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断是否有相机应用
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, PhotoUtil.shooting_thumbnail);
        }
    }

    public void shooting(Activity activity,int REQUEST_TAKE_PHOTO){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {//判断是否有相机应用
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();//创建临时图片文件
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                //FileProvider 是一个特殊的 ContentProvider 的子类，
                //它使用 content:// Uri 代替了 file:/// Uri. ，更便利而且安全的为另一个app分享文件
                Uri photoURI = FileProvider.getUriForFile(activity,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    /**
     * 创建图片路径
     */
    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //创建临时文件,文件前缀不能少于三个字符,后缀如果为空默认未".tmp"
        File image = File.createTempFile(
                imageFileName,  /* 前缀 */
                ".jpg",         /* 后缀 */
                storageDir      /* 文件夹 */
        );
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
