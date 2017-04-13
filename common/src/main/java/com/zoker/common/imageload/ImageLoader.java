package com.zoker.common.imageload;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 *
 * Created by Zoker on 2017/4/13.
 */

public class ImageLoader {
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
    public static void loadImage(Context context, Uri uri, ImageView imageView){
        Glide.with(context).load(uri).into(imageView);
    }
}
