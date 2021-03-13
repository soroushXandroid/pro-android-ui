package com.soroush.protrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilPassword, tilUsername;
    private TextInputEditText etPassword;
    private MaterialButton btnNext, btnCancel;
    private ImageView ivLogo;
    private AppCompatTextView tvLogoName;
    private LottieAnimationView lottieLoading, lottieConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findEelements();
        starterAnimation();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottieLoading.setVisibility(View.VISIBLE);
                lottieLoading.setAlpha(1f);
                lottieLoading.playAnimation();
                if (isValidPass(etPassword.getEditableText())){

                    lottieLoading.animate().alpha(0f).setDuration(1000).
                            setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            lottieLoading.setVisibility(View.GONE);
                            lottieLoading.pauseAnimation();
                            lottieLoading.cancelAnimation();

                            tilPassword.setError(null);

                            lottieConf.setVisibility(View.VISIBLE);
                            lottieConf.playAnimation();
                            lottieConf.addAnimatorListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    Intent intent = new Intent(LoginActivity.this, UserPanel.class);
                                    //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                    startActivity(intent);
                                    lottieConf.setVisibility(View.GONE);
                                    lottieConf.pauseAnimation();
                                    lottieConf.cancelAnimation();
                                }});

                        }});

                } else {

                    if (tilPassword.getError() == null) {
                        lottieLoading.animate().alpha(0f).setDuration(1000).
                                setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        tilPassword.setError("pass is not valid!");
                                        lottieLoading.setVisibility(View.GONE);
                                        lottieLoading.pauseAnimation();
                                        lottieLoading.cancelAnimation();
                                    }
                                });
                    } else {
                        lottieLoading.setVisibility(View.GONE);
                        lottieLoading.pauseAnimation();
                        lottieLoading.cancelAnimation();
                    }

                } }});



    }

    private void starterAnimation() {

        tilPassword.setVisibility(View.GONE);
        tilUsername.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        ivLogo.setVisibility(View.GONE);
        tvLogoName.setVisibility(View.GONE);

        tilPassword.setAlpha(0f);
        tilUsername.setAlpha(0f);
        btnCancel.setAlpha(0f);
        btnNext.setAlpha(0f);
        ivLogo.setAlpha(0f);
        tvLogoName.setAlpha(0f);

        ivLogo.setTranslationX(200f);
        tvLogoName.setTranslationX(250f);
        tilUsername.setTranslationX(-200f);
        tilPassword.setTranslationX(-250f);
        btnNext.setTranslationY(200f);
        btnCancel.setTranslationY(250f);

        tilPassword.setVisibility(View.VISIBLE);
        tilUsername.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        ivLogo.setVisibility(View.VISIBLE);
        tvLogoName.setVisibility(View.VISIBLE);

        int shortTimeDuration = 1500;
        ivLogo.animate().alpha(1f).translationX(0f).setDuration(shortTimeDuration).setListener(null);
        tvLogoName.animate().alpha(1f).translationX(0f).setDuration(shortTimeDuration + 500).setListener(null);
        tilUsername.animate().alpha(1f).translationX(0f).setDuration(shortTimeDuration).setListener(null);
        tilPassword.animate().alpha(1f).translationX(0f).setDuration(shortTimeDuration + 500).setListener(null);
        btnNext.animate().alpha(1f).translationY(0f).setDuration(shortTimeDuration).setListener(null);
        btnCancel.animate().alpha(1f).translationY(0f).setDuration(shortTimeDuration + 500).setListener(null);

        //lottieLoading.setVisibility(View.VISIBLE);
        lottieLoading.playAnimation();
        lottieLoading.animate().alpha(0f).setDuration(shortTimeDuration + 500)
        .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                lottieLoading.setVisibility(View.GONE);
                lottieLoading.pauseAnimation();
                lottieLoading.cancelAnimation();
            }});

    }

    private void findEelements() {
        tilPassword = findViewById(R.id.input_password);
        tilUsername = findViewById(R.id.input_username);
        etPassword = findViewById(R.id.et_password);
        //etUsername = findViewById(R.id.et_username);
        btnNext = findViewById(R.id.btn_next);
        btnCancel = findViewById(R.id.btn_cancel);
        ivLogo = findViewById(R.id.iv_logo);
        tvLogoName = findViewById(R.id.textView);
        lottieLoading = findViewById(R.id.lottie_login);
        lottieConf = findViewById(R.id.lottie_confirmation);
    }

    private boolean isValidPass(Editable text){
        return (text.length() >= 5);
    }


}
