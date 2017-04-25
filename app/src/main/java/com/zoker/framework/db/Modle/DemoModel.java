package com.zoker.framework.db.Modle;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.zoker.framework.db.DemoDatabase;

/**
 * Created by Zoker on 2017/4/13.
 */
@ModelContainer
@Table(databaseName = DemoDatabase.NAME)
public class DemoModel extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)   //自增id
    public Long id;

    @Column
    public String name;

    @Column
    public int sex;
}
