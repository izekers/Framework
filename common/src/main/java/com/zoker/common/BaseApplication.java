package com.zoker.common;

import android.app.Application;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 *
 * Created by Zoker on 2017/4/12.
 */

public class BaseApplication extends Application{
    public static BaseApplication Instance;
    public void onCreate() {
        Instance = this;
        super.onCreate();
        FlowManager.init(this);
    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
