package com.soroush.protrial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soroush.protrial.userpanel.RemoveConfirmation;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder>{

    private List<BrandModel> list;
    private onItemRemove listener;
    public OrderAdapter(List<BrandModel> list, onItemRemove listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.basket_orders, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        final BrandModel model = list.get(position);
        holder.initViews(model);
        holder.ivRemove.setOnClickListener(v -> listener.onRemove(model));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onItemRemove{
        void onRemove(BrandModel model);
    }

}
