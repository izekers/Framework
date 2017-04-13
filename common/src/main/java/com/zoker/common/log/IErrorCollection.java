package com.zoker.common.log;

import android.content.Context;

/**
 * 打印统计接口
 * Created by Zekers on 2016/11/28.
 */
public interface IErrorCollection {
    void reportError(Context context, String error);
    void reportError(Context context, Throwable e);
}
