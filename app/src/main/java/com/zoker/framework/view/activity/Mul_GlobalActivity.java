package com.zoker.framework.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zoker.common.BaseActivity;
import com.zoker.framework.R;
import com.zoker.framework.model.ItemModel1;
import com.zoker.framework.model.ItemModel2;
import com.zoker.framework.view.ViewHolder.Item1ViewProvider;
import com.zoker.framework.view.ViewHolder.Item2ViewProvider;

import me.drakeet.multitype.GlobalMultiTypePool;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 全局化的MultiType库的使用
 * Created by Zoker on 2017/4/21.
 */

public class Mul_GlobalActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalProvider();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Items items=getData();

        MultiTypeAdapter multiTypeAdapter=new MultiTypeAdapter(items);
        //将全局的类型加入到局部适配器来
        //全局的添加在Application中实现了，当然其他地方也能实现
        multiTypeAdapter.applyGlobalMultiTypePool();

        recyclerView.setAdapter(multiTypeAdapter);
    }

    //此方法最好在Application中调用
    public void globalProvider(){
        //MultiType库的全局添加Item
        GlobalMultiTypePool.register(ItemModel1.class, new Item1ViewProvider());
        GlobalMultiTypePool.register(ItemModel2.class, new Item2ViewProvider());
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
