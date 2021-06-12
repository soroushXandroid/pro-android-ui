package com.soroush.protrial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.airbnb.lottie.SimpleColorFilter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Locale;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearBack;
    private AppCompatTextView tvTitle, tvAmount, tvPrice;
    private AppCompatImageView ivPic;
    private AppCompatImageView mbPlus, mbMinus;
    private ExtendedFloatingActionButton fabBuy;

    int id, amount;
    private AppDatabase db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_product);
        bindElements();

        id = getIntent().getIntExtra("id", 0);
        db = new AppDatabase(this);
        BrandModel model = db.getThisItem(id);
        initViews(model);
        Log.i("TAG", "onCreate: ");
        linearBack.setOnClickListener(this);
        mbPlus.setOnClickListener(this);
        mbMinus.setOnClickListener(this);
        fabBuy.setOnClickListener(this);

        mbPlus.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                mbPlus.getDrawable().setColorFilter(new SimpleColorFilter(0xDD000000));
                mbPlus.invalidate();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                mbPlus.getDrawable().clearColorFilter();
                mbPlus.invalidate();
            }
            return false; });

        mbMinus.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                mbMinus.getDrawable().setColorFilter(new SimpleColorFilter(0xDD000000));
                mbMinus.invalidate();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                mbMinus.getDrawable().clearColorFilter();
                mbMinus.invalidate();
            }
            return false; });

    }

    private void bindElements(){
        linearBack = findViewById(R.id.linear_back_button);
        tvTitle = findViewById(R.id.tv_product_title);
        tvAmount = findViewById(R.id.tv_amount);
        tvPrice = findViewById(R.id.tv_show_price);
        ivPic = findViewById(R.id.iv_product_pic);
        mbPlus = findViewById(R.id.mb_plus);
        mbMinus = findViewById(R.id.mb_minus);
        fabBuy = findViewById(R.id.fab_buy_now);
    }

    private void initViews(BrandModel model) {

        tvTitle.setText(model.getTitle());
        amount = 1;
        tvAmount.setText(String.valueOf(amount));
        tvPrice.setText(String.format(Locale.getDefault(), "$%d.00", model.getPrice()));

        int mod = id % 4;
        int imgRes;
        switch (mod){

            case 2:
                imgRes = R.drawable.opt_th;
                break;

            case 3:
                imgRes = R.drawable.opt_se;
                break;

            default:
                imgRes = R.drawable.opt_first;
                break;

        }
        ivPic.setImageResource(imgRes);

    }

    private void hideStatusBar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.linear_back_button:
                this.onBackPressed();
                break;

            case R.id.mb_plus:
                tvAmount.setText(String.valueOf(++amount));
                break;

            case R.id.mb_minus:
                if (amount > 1)
                    tvAmount.setText(String.valueOf(--amount));
                break;

            case R.id.fab_buy_now:
                db.setLock(id, amount);
                Intent intent = new Intent();
                intent.setClass(this, ConfirmActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            default:break;

        }

    }

}
