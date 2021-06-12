package com.soroush.protrial;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class BrandHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView ivImage;
    AppCompatImageView ivAdd;
    AppCompatTextView tvTitle, tvPrice;
    LinearLayout linearSelect;

    public BrandHolder(@NonNull View itemView) {
        super(itemView);
        ivImage = itemView.findViewById(R.id.iv_image);
        ivAdd = itemView.findViewById(R.id.iv_add);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvPrice = itemView.findViewById(R.id.tv_price);
        linearSelect = itemView.findViewById(R.id.linear_select);
    }

    void initItems(BrandModel model){


        int price = model.getPrice();
        byte[] imgRes = model.getImg();
        String title = model.getTitle();

        tvTitle.setText(title);
        tvPrice.setText(price + "$");
        ivImage.setImageBitmap(BitmapFactory.decodeByteArray(imgRes, 0, imgRes.length));

    }

}
