package com.zoker.framework.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zoker.common.BaseActivity;
import com.zoker.framework.R;
import com.zoker.framework.model.ItemModel1;
import com.zoker.framework.model.ItemModel2;
import com.zoker.framework.view.ViewHolder.Item1ViewProvider;
import com.zoker.framework.view.ViewHolder.Item2ViewProvider;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 最简单的MultiType库的使用
 * Created by Zoker on 2017/4/21.
 */

public class Mul_SimpleActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Items items=getData();

        MultiTypeAdapter multiTypeAdapter=new MultiTypeAdapter(items);
        multiTypeAdapter.register(ItemModel1.class,new Item1ViewProvider());
        multiTypeAdapter.register(ItemModel2.class,new Item2ViewProvider());

        recyclerView.setAdapter(multiTypeAdapter);
    }

    public Items getData(){
        Items items=new Items();
        for (int i=0;i<20;i++){
            items.add(new ItemModel1("类型1"+i));
            items.add(new ItemModel2("类型2"+i));
            items.add(new ItemModel2("类型2"+i));
        }
        return items;
    }
}
