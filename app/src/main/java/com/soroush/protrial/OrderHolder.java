package com.soroush.protrial;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

class OrderHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView tvTitle, tvPrice;
    private AppCompatImageView ivPic;
    AppCompatImageView ivRemove;

    public OrderHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_basket_item_title);
        tvPrice = itemView.findViewById(R.id.tv_basket_item_price);
        ivPic = itemView.findViewById(R.id.iv_item_pic);
        ivRemove = itemView.findViewById(R.id.iv_remove_item);
    }

    void initViews(BrandModel model) {

        String title = model.getTitle();
        String price = model.getDesc();
        int id = model.getId();

        tvTitle.setText(title);
        tvPrice.setText(price + "$");

        int var = id % 5;
        int imgRes = 0;
        switch (var){

            case 0:
                imgRes = R.drawable.fi;
                break;

            case 1:
                imgRes = R.drawable.se;
                break;

            case 2:
                imgRes = R.drawable.th;
                break;

            case 3:
                imgRes = R.drawable.ph_first;
                break;

            case 4:
                imgRes = R.drawable.ph_second;
                break;

        }
        ivPic.setImageResource(imgRes);
    }


}



