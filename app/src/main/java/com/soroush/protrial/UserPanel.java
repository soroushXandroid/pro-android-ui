package com.soroush.protrial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.soroush.protrial.userpanel.BasketFragment;
import com.soroush.protrial.userpanel.LapTopFragment;
import com.soroush.protrial.userpanel.MobileFragment;
import com.soroush.protrial.userpanel.RadioFragment;
import com.soroush.protrial.userpanel.TvFragment;

public class UserPanel extends AppCompatActivity implements BrandAdapter.onClickItemListener {

    LinearLayout linear;
    TabLayout tabLayout;
    Fragment fragment = null;
    FragmentTransaction fTransaction;
    FragmentManager fManager;
    private SharedPreferences pref;
    AppCompatTextView tvBadge;
    private int badgeNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_user);
        findElements();
        Toolbar toolbar = findViewById(R.id.panel_toolbar);
        bindToolbar(toolbar);

        linear.setBackgroundResource(R.drawable.input_bg);

        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                R.color.sefid), PorterDuff.Mode.SRC_IN);
        initMainFragment();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                tab.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.sefid), PorterDuff.Mode.SRC_IN);

                switch (tab.getPosition()){

                    case 0:
                        fragment = new TvFragment(UserPanel.this, UserPanel.this);
                        break;

                    case 1:
                        fragment = new RadioFragment();
                        break;

                    case 2:
                        fragment = new MobileFragment();
                        break;

                    case 3:
                        fragment = new LapTopFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                assert fragment != null;
                ft.replace(R.id.panel_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.textIconPrimary), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.basket);
        View actionView = menuItem.getActionView();
        tvBadge = actionView.findViewById(R.id.cart_badge);

        updateBadge(badgeNumber);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }});

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.basket) {

            fManager = getSupportFragmentManager();
            fTransaction = fManager.beginTransaction();
            fragment = new BasketFragment(this, new BasketFragment.onRemoveSubmited() {
                @Override
                public void onRemove() {
                    updateBadge(--badgeNumber);
                }});
            fTransaction.setCustomAnimations(R.anim.right_in, R.anim.right_out);
            fTransaction.replace(R.id.panel_container, fragment);
            fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fTransaction.commit();

        }

        return true;
    }

    private void updateBadge(int counter) {
        if (counter <= 0){
            tvBadge.setVisibility(View.INVISIBLE);
            counter = 0;
        }
        else if (tvBadge.getVisibility() == View.INVISIBLE){
            tvBadge.setVisibility(View.VISIBLE);
        }
        tvBadge.setText(String.valueOf(counter));
    }

    private void findElements(){
        linear = findViewById(R.id.nsv);
        tabLayout = findViewById(R.id.tb_layout);
    }

    private void bindToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new
                NavigationIconClickListener(
                this,
                linear,
                new AccelerateDecelerateInterpolator(),
                getDrawable(R.drawable.ic_person),
                getDrawable(R.drawable.ic_close),
                toolbar));
    }

    private void initMainFragment(){
        fManager = getSupportFragmentManager();
        fTransaction = fManager.beginTransaction();
        fragment = new TvFragment(this, this);
        fTransaction.replace(R.id.panel_container, fragment);
        fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fTransaction.commit();
    }

    @Override
    public void onSelected() {
        updateBadge(++badgeNumber);
    }

    @Override
    public void onUnSelected() {
        updateBadge(--badgeNumber);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pref = getSharedPreferences("badge", Context.MODE_PRIVATE);
        badgeNumber = pref.getInt("badge-counter", 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("badge-counter", badgeNumber);
        editor.apply();
    }

}
