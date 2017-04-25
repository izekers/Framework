package com.zoker.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2017/4/24.
 */

public class ProgressUtil {
    //信息显示对话框
    public void showSelectMsg(Context context, String title, final SelectCallBack callBack) {
        new AlertDialog.Builder(context).setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callBack.ok();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callBack.cancel();
                    }
                }).create().show();
    }
    //选择对话框
    public void showDialogMsg(Context context,String title,String msg) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }
    //列表对话框
    public void showListDialog(Activity activity, CharSequence[] itemsStr, String title, final ItemSelectCallBack callBack){
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setItems(itemsStr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack!=null){
                            callBack.onItemSelect(dialog,which);
                        }
                    }
                })
                .create().show();
    }
    interface ItemSelectCallBack{
        void onItemSelect(DialogInterface dialog,int position);
    }
    interface SelectCallBack {
        void cancel();

        void ok();
    }

}
