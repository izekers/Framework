package com.zoker.framework.view.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zoker.framework.R;
import com.zoker.framework.model.IntentModel;
import com.zoker.framework.model.ItemModel1;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Item1ViewProvider extends ItemViewBinder<ItemModel1,Item1ViewProvider.ViewHolder>{
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view=inflater.inflate(R.layout.item1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemModel1 item) {
        holder.item.setText(item.toString());
        holder.setModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemModel1 model;
        TextView item;
        public ViewHolder(final View itemView) {
            super(itemView);
            item=(TextView) itemView.findViewById(R.id.item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),model.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setModel(ItemModel1 model) {
            this.model = model;
        }
    }
}
