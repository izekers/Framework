package com.zoker.common;

import android.app.Application;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.zoker.common.module.IModule;
import com.zoker.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Zoker on 2017/4/12.
 */

public class BaseApplication extends Application{
    public static BaseApplication Instance;

    public static List<IModule> modules=new ArrayList<>();
    public void onCreate() {
        Instance = this;
        super.onCreate();

        init();
    }


    public  void init(){
        FlowManager.init(this);
        initModule();
        setupModule();
    }

    public void initModule(){

    }

    public void addModule(IModule module){
        modules.add(module);
    }

    public void setupModule(){
        for (IModule module:modules){
            module.setup();
        }
    }

    //工具方法块

    /**
     * 吐司
     */
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    /**
     * 是否联网
     */
    public boolean isConnnected(){
        return NetworkUtil.checkNetwork(this);
    }
}
