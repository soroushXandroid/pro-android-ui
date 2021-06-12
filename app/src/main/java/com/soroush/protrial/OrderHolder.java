package com.soroush.protrial;

import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

class OrderHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView tvTitle, tvPrice, tvAmount;
    private AppCompatImageView ivPic;
    AppCompatImageView ivRemove;

    public OrderHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_basket_item_title);
        tvPrice = itemView.findViewById(R.id.tv_basket_item_price);
        ivPic = itemView.findViewById(R.id.iv_item_pic);
        ivRemove = itemView.findViewById(R.id.iv_remove_item);
        tvAmount = itemView.findViewById(R.id.tv_order_amount);
    }

    void initViews(BrandModel model) {

        String title = model.getTitle();
        int price = model.getPrice();
        int amount = model.getAmount();
        byte[] imgRes = model.getImg();

        tvTitle.setText(title);
        tvPrice.setText(String.format(Locale.getDefault(), "$%d", price));
        tvAmount.setText(String.format(Locale.getDefault(), "amount : %d", amount));
        ivPic.setImageBitmap(BitmapFactory.decodeByteArray(imgRes, 0, imgRes.length));

    }


}



