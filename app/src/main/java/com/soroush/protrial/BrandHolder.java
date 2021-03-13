package com.soroush.protrial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class BrandHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView ivImage;
    AppCompatTextView tvTitle, tvDesc;
    LinearLayout linearSelect;

    public BrandHolder(@NonNull View itemView) {
        super(itemView);
        ivImage = itemView.findViewById(R.id.iv_image);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDesc = itemView.findViewById(R.id.tv_desc);
        linearSelect = itemView.findViewById(R.id.linear_select);
    }

    void initItems(BrandModel model, Context context){

        int id = model.getId();
        int lock = model.getLock();

        tvTitle.setText(model.getTitle());
        tvDesc.setText(model.getDesc() + "$");

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
        ivImage.setImageResource(imgRes);

        if (lock == 1){
            linearSelect.setSelected(true);
            tvTitle.setTextColor(context.getColor(R.color.khakestari));
            tvDesc.setTextColor(context.getColor(R.color.khakestari));
        }

    }

}
