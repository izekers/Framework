package com.zoker.framework.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zoker.common.BaseActivity;
import com.zoker.framework.R;
import com.zoker.framework.model.IntentModel;
import com.zoker.framework.view.ViewHolder.DemoViewProvider;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 *
 * Created by Zoker on 2017/4/21.
 */

public class MultiTypeActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Items items=getData();

        MultiTypeAdapter multiTypeAdapter=new MultiTypeAdapter(items);
        multiTypeAdapter.register(IntentModel.class,new DemoViewProvider());

        recyclerView.setAdapter(multiTypeAdapter);
    }

    public Items getData(){
        Items items=new Items();
        items.add(new IntentModel("最简单的实现",new Intent(MultiTypeActivity.this,Mul_SimpleActivity.class)));
        items.add(new IntentModel("全局化的实现",new Intent(MultiTypeActivity.this,Mul_GlobalActivity.class)));
        return items;
    }
}
