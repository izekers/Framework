package com.zoker.common;


import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
/**
 *
 * Created by Zoker on 2017/4/12.
 */

public abstract class BaseActivity<V,P> extends MvpActivity{


    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
