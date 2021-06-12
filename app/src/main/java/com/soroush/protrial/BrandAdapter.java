package com.soroush.protrial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.SimpleColorFilter;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandHolder> {

    private List<BrandModel> list;
    private AddItemClick itemClick;
    private AppDatabase db;
    private Context context;
    public BrandAdapter(List<BrandModel> list, AppDatabase db, AddItemClick itemClick){
        this.list = list;
        this.db = db;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public BrandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).
                inflate(R.layout.card_model, parent, false);
        return new BrandHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull BrandHolder holder, int position) {

        final BrandModel model = list.get(position);
        holder.initItems(model);

        holder.ivAdd.setOnClickListener(v -> {
            db.updateAmount(model.getId(), true);
            itemClick.onAddItem();
        });

        holder.ivAdd.setOnTouchListener((view, motionEvent) -> {

            AppCompatImageView iv = (AppCompatImageView) view;
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                iv.getDrawable().setColorFilter(new SimpleColorFilter(0xEE068575));
             else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                iv.getDrawable().clearColorFilter();
            iv.invalidate();
            return false; });

        holder.linearSelect.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(context, ProductActivity.class);
            intent.putExtra("id", model.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*private void selectedAction(View v, int id, AppCompatTextView tvTitle, AppCompatTextView tvDesc){

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

    }*/

    public interface AddItemClick{
        void onAddItem();
    }


}
