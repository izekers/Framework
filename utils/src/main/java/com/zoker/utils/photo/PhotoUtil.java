package com.zoker.utils.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoker on 2017/4/20.
 */
public class PhotoUtil {
    //摄影缩略图
    public static final int shooting_thumbnail = 3;


    private PhotoUtil() {

    }

    private static Photographer with(Activity activity) {
        return new Photographer(activity);
    }

    public static void onDestory(Activity activity) {
        if (Photographer.recallMap.get(activity) != null)
            Photographer.recallMap.remove(activity);
    }

    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        PhotoRecall recall = Photographer.recallMap.get(activity);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == shooting_thumbnail) {
                thumbnailResult(data);
            }
        }
    }


    public static Bitmap thumbnailResult(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        return imageBitmap;
    }
}
