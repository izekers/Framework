package com.zoker.common;

import android.app.Application;
import android.widget.Toast;

/**
 *
 * Created by Zoker on 2017/4/12.
 */

public class BaseApplication extends Application{
    public static BaseApplication Instance;
    public void onCreate() {
        Instance = this;
        super.onCreate();
    }
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
