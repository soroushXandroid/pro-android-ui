package com.soroush.protrial.userpanel;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.soroush.protrial.App;
import com.soroush.protrial.AppDatabase;
import com.soroush.protrial.BrandAdapter;
import com.soroush.protrial.BrandModel;
import com.soroush.protrial.ImagePager;
import com.soroush.protrial.R;

import java.util.ArrayList;
import java.util.List;

public class TvFragment extends Fragment {

    private RecyclerView rcSamsung, rcApple, rcLg;
    private TabLayout tabLayout;
    private ViewPager fPager;
    private Handler handler;
    private Runnable runnable;
    private List<BrandModel> sList, aList, lList;
    private Context context;
    private AppDatabase db;

    private int page = 0;
    private BrandAdapter.onClickItemListener listener;
    public TvFragment(Context context, BrandAdapter.onClickItemListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findElements(view);

        handler = new Handler();
        setUpPagers();
        setUpRecyclers();

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void setUpPagers() {

        final ImagePager pager = new ImagePager(context, App.imageList);
        fPager.setAdapter(pager);
        tabLayout.setupWithViewPager(fPager, true);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (pager.getCount() == page)
                    page = 0;
                else
                    ++page;
                fPager.setCurrentItem(page, true);
                handler.postDelayed(this, 3000);
            }};

        fPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setUpRecyclers() {
        sList = new ArrayList<>();
        aList = new ArrayList<>();
        lList = new ArrayList<>();

        rcSamsung.setHasFixedSize(true);
        rcApple.setHasFixedSize(true);
        rcLg.setHasFixedSize(true);

        rcSamsung.setLayoutManager(new LinearLayoutManager
                (context, RecyclerView.HORIZONTAL, false));
        rcApple.setLayoutManager(new LinearLayoutManager
                (context, RecyclerView.HORIZONTAL, false));
        rcLg.setLayoutManager(new LinearLayoutManager
                (context, RecyclerView.HORIZONTAL, false));

        db = new AppDatabase(context);
        sList = db.getAllData(App.SAMSUNG_TABLE);
        aList = db.getAllData(App.APPLE_TABLE);
        lList = db.getAllData(App.LG_TABLE);
        db.close();

        BrandAdapter sAdapter, aAdapter, lAdapter;
        sAdapter = new BrandAdapter(sList, db, listener);
        aAdapter = new BrandAdapter(aList, db, listener);
        lAdapter = new BrandAdapter(lList, db, listener);
        rcSamsung.setAdapter(sAdapter);
        rcApple.setAdapter(aAdapter);
        rcLg.setAdapter(lAdapter);
    }

    private void findElements(View v){
        rcSamsung = v.findViewById(R.id.recycler_tv_samsung);
        rcApple = v.findViewById(R.id.recycler_tv_apple);
        rcLg = v.findViewById(R.id.recycler_tv_lg);
        fPager = v.findViewById(R.id.pager_first);
        tabLayout = v.findViewById(R.id.dot_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

}
