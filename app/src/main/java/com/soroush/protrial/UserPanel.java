package com.soroush.protrial;

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
import androidx.appcompat.content.res.AppCompatResources;
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

import java.util.List;

public class UserPanel extends AppCompatActivity implements BrandAdapter.AddItemClick{

    LinearLayout linear;
    TabLayout tabLayout;
    Fragment fragment = null;
    FragmentTransaction fTransaction;
    FragmentManager fManager;
    private AppCompatTextView tvBadge;
    private int badgeNumber = 0;

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
                        fragment = new TvFragment(UserPanel.this);
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

        AppDatabase db = new AppDatabase(this);
        List<BrandModel> list = db.getAllBasket();

        for (BrandModel model : list){
            badgeNumber += model.getAmount();
        }
        updateBadge(badgeNumber);
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.basket) {

            fManager = getSupportFragmentManager();
            fTransaction = fManager.beginTransaction();
            fragment = new BasketFragment(this, amount -> {
                badgeNumber -= amount;
                updateBadge(badgeNumber);
            });
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
        else {
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
                AppCompatResources.getDrawable(this, R.drawable.ic_person),
                AppCompatResources.getDrawable(this, R.drawable.ic_close),
                toolbar));
    }

    private void initMainFragment(){
        fManager = getSupportFragmentManager();
        fTransaction = fManager.beginTransaction();
        fragment = new TvFragment(this);
        fTransaction.replace(R.id.panel_container, fragment);
        fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fTransaction.commit();
    }

    @Override
    public void onAddItem() {
        updateBadge(++badgeNumber);
    }

}
