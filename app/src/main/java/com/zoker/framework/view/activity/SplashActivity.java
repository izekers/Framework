package com.zoker.framework.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zoker.common.BaseActivity;

/**
 *
 * Created by Zoker on 2017/4/21.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
