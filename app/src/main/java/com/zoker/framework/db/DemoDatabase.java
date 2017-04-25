package com.zoker.framework.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * 数据库表
 * Created by Zoker on 2017/4/13.
 */
@Database(name = DemoDatabase.NAME, version = DemoDatabase.VERSION)
public class DemoDatabase {
    //数据库名称
    public static final String NAME = "DemoDatabase";
    //数据库版本号
    public static final int VERSION = 1;
}
