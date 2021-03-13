package com.soroush.protrial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandHolder> {

    private List<BrandModel> list;
    private onClickItemListener listener;
    private AppDatabase db;
    private Context context;
    public BrandAdapter(List<BrandModel> list, AppDatabase db, onClickItemListener listener){
        this.list = list;
        this.db = db;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BrandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).
                inflate(R.layout.card_model, parent, false);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandHolder holder, int position) {

        BrandModel model = list.get(position);
        holder.initItems(model, context);
        final AppCompatTextView tvDesc = holder.tvDesc, tvTitle = holder.tvTitle;
        final int id = model.getId();

        holder.linearSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedAction(v, id, tvTitle, tvDesc);

            }});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void selectedAction(View v, int id, AppCompatTextView tvTitle, AppCompatTextView tvDesc){

        if (v.isSelected()) {
            v.setSelected(false);
            tvTitle.setTextColor(context.getColor(R.color.textIconPrimary));
            tvDesc.setTextColor(context.getColor(R.color.textIconPrimary));
            listener.onUnSelected();
            db.updateBasket(App.SAMSUNG_TABLE, id, 0);
        }
        else {
            v.setSelected(true);
            tvTitle.setTextColor(context.getColor(R.color.khakestari));
            tvDesc.setTextColor(context.getColor(R.color.khakestari));
            listener.onSelected();
            db.updateBasket(App.SAMSUNG_TABLE, id, 1);
        }

    }

    public interface onClickItemListener{
        void onSelected();
        void onUnSelected();
    }


}
