package com.zoker.common;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.zoker.utils.KeyboardUtil;
import com.zoker.utils.photo.PhotoUtil;
import com.zoker.utils.photo.Photographer;

/**
 *
 * Created by Zoker on 2017/4/12.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private InputMethodManager manager=null;
    private boolean isKeyBorderHide=false;

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyboardUtil.hintKbTwo(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtil.onActivityResult(this,requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PhotoUtil.onDestory(this);
    }

    //点击空白地区隐藏
    protected void isKeyBorder_SpaceHide(Boolean isSpaceHide){
        this.isKeyBorderHide=isSpaceHide;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (isKeyBorderHide){
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (getCurrentFocus() != null
                        && getCurrentFocus().getWindowToken() != null) {
                    manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
