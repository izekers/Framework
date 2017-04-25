package com.zoker.framework.view.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoker.framework.R;
import com.zoker.framework.model.IntentModel;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/4/21.
 */

public class DemoViewProvider extends ItemViewBinder<IntentModel,DemoViewProvider.ViewHolder>{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view=inflater.inflate(R.layout.item_demo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull IntentModel item) {
        holder.item.setText(item.getTitle());
        holder.setModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        IntentModel model;
        TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            item=(TextView) itemView.findViewById(R.id.item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.getContext().startActivity(model.getIntent());
                }
            });
        }

        public void setModel(IntentModel model) {
            this.model = model;
        }
    }
}
