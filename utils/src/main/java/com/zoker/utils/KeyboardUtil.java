package com.zoker.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * 键盘工具
 * Created by Zoker on 2017/4/12.
 */

public class KeyboardUtil {
    /**
     * 一些对于键盘的控制
     在manifest文件中可以设置Activity的android:windowSoftInputMode属性，这个属性值常见的设置如下：
     android:windowSoftInputMode="stateAlwaysHidden|adjustPan"
     那么这里值的含义列表如下：
     【A】stateUnspecified：软键盘的状态并没有指定，系统将选择一个合适的状态或依赖于主题的设置
     【B】stateUnchanged：当这个activity出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示
     【C】stateHidden：用户选择activity时，软键盘总是被隐藏
     【D】stateAlwaysHidden：当该Activity主窗口获取焦点时，软键盘也总是被隐藏的
     【E】stateVisible：软键盘通常是可见的
     【F】stateAlwaysVisible：用户选择activity时，软键盘总是显示的状态
     【G】adjustUnspecified：默认设置，通常由系统自行决定是隐藏还是显示
     【H】adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间
     【I】adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分
     */

    //点击空白处隐藏键盘，设置在setContentView后面
    public static void spaceHint(Activity activity){
        new KeyboardUtil(activity);
    }

    //关闭软键盘
    public static void hintKbTwo(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(activity.getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //打开键盘
    public static void showKeyboard(View view, Context mContext){
        if (view==null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    //关闭软键盘
    public static void closeSoftKeybord(View view, Context mContext) {
        if (view==null)
            return;
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private KeyboardUtil(final Activity activity) {
        ViewGroup content = (ViewGroup) activity.findViewById(android.R.id.content);
        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dispatchTouchEvent(activity, motionEvent);
                return false;
            }
        });
        getScrollView(content, activity);
    }

    private void getScrollView(ViewGroup viewGroup, final Activity activity) {
        if (null == viewGroup) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ScrollView) {
                ScrollView newDtv = (ScrollView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);

                        return false;
                    }
                });
            } else if (view instanceof AbsListView) {
                AbsListView newDtv = (AbsListView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);

                        return false;
                    }
                });
            } else if (view instanceof ViewGroup) {

                this.getScrollView((ViewGroup) view, activity);
            }
        }
    }

    /**
     * @param mActivity
     * @param ev
     * @return
     */
    private boolean dispatchTouchEvent(Activity mActivity, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = mActivity.getCurrentFocus();
            if (null != v && isShouldHideInput(v, ev)) {
                hideSoftInput(mActivity, v.getWindowToken());
            }
        }
        return false;
    }

    /**
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            Rect rect = new Rect();
            v.getHitRect(rect);
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param mActivity
     * @param token
     */
    private void hideSoftInput(Activity mActivity, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



}
