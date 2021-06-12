package com.soroush.protrial;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;
import java.util.Locale;

public class ConfirmActivity extends AppCompatActivity{

    AppCompatTextView tvCreditPrice, tvCost, tvShipping, tvTax, tvTotal;
    private LinearLayout linearBack;
    private AppDatabase db;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        bindElements();

        id = getIntent().getIntExtra("id", 0);

        db = new AppDatabase(this);
        int cost = 0;

        if (id != 0) {
            BrandModel model = db.getThisItem(id);
            cost += model.getLock() * model.getPrice();
        } else {
            List<BrandModel> list = db.getAllBasket();
            for (BrandModel model : list)
                cost += (model.getAmount() * model.getPrice());
        }

        tvCreditPrice.setText(String.format(Locale.getDefault(), "$%d.00", cost));
        tvCost.setText(String.format(Locale.getDefault(), "$ %d.00", cost));
        tvShipping.setText(String.format(Locale.getDefault(), "$ %.2f", 33.26));
        tvTax.setText(String.format(Locale.getDefault(), "$ %.2f", 11.06));

        double total = cost + 33.26 + 11.06;
        tvTotal.setText(String.format(Locale.getDefault(), "$ %.2f", total));

        linearBack.setOnClickListener(v -> {
            this.onBackPressed();
        });

    }

    private void bindElements() {
        tvCreditPrice = findViewById(R.id.tv_credit_price);
        tvCost = findViewById(R.id.tv_cost_price);
        tvShipping = findViewById(R.id.tv_shiping_price);
        tvTax = findViewById(R.id.tv_tax_price);
        tvTotal = findViewById(R.id.tv_total_price);
        linearBack = findViewById(R.id.linear_confirm_back);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (id != 0){
            db.setLock(id, 0);
        }
    }

}