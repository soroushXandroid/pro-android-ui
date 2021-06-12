package com.soroush.protrial.userpanel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.SimpleColorFilter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.soroush.protrial.AppDatabase;
import com.soroush.protrial.BrandModel;
import com.soroush.protrial.ConfirmActivity;
import com.soroush.protrial.OrderAdapter;
import com.soroush.protrial.R;

import java.util.ArrayList;
import java.util.List;


public class BasketFragment extends Fragment implements OrderAdapter.onItemRemove{

    private RecyclerView recycler;
    private OrderAdapter adapter;
    private AppDatabase db;
    private Context context;
    private List<BrandModel> list;
    private onRemoveSubmited listener;

    private AppCompatImageView ivBack;
    private ExtendedFloatingActionButton fabPay;
    private RemoveConfirmation btnSheetFragment;

    public BasketFragment(Context context, onRemoveSubmited listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        findElements(view);
        setUpRecycler();
        backArrowSetup();
        updatePrice();

        fabPay.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(context, ConfirmActivity.class);
            context.startActivity(intent);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    private void updatePrice(){

        int totalPrice = 0;
        if (!list.isEmpty()) {

            for (BrandModel model : list) {
                totalPrice += model.getAmount() * model.getPrice();
            }

        } else {
            fabPay.hide();
        }
        fabPay.setText(getResources().getText(R.string.payment));
        fabPay.append(" - (" + totalPrice + "$)");

    }

    private void setUpRecycler() {

        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recycler.setItemAnimator(new DefaultItemAnimator());

        db = new AppDatabase(context);
        list = db.getAllBasket();
        adapter = new OrderAdapter(list, this);
        recycler.setAdapter(adapter);
        db.close();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void backArrowSetup(){

        ivBack.setOnClickListener(v -> {
            FragmentManager fManager = getParentFragmentManager();
            FragmentTransaction fTransaction = fManager.beginTransaction();
            Fragment fragment = new TvFragment(context);
            fTransaction.setCustomAnimations(R.anim.right_in, R.anim.right_out);
            fTransaction.replace(R.id.panel_container, fragment);
            fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fTransaction.commit();
        });

        ivBack.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                ivBack.getDrawable().setColorFilter(new SimpleColorFilter(0xEEEEEEEE));
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                ivBack.getDrawable().clearColorFilter();
            ivBack.invalidate();

            return false;
        });

    }

    private void findElements(View view) {
        recycler = view.findViewById(R.id.basket_recycler);
        ivBack = view.findViewById(R.id.iv_arrow_back);
        fabPay = view.findViewById(R.id.fab_payment);
    }

    @Override
    public void onRemove(final BrandModel model) {

        btnSheetFragment = new RemoveConfirmation(new RemoveConfirmation.onConfirmationRemove() {
            @Override
            public void confRemove() {
                db.updateAmount(model.getId(), false);
                list.remove(model);
                adapter.notifyDataSetChanged();
                updatePrice();
                listener.onRemove(model.getAmount());
                btnSheetFragment.dismiss();
            }

            @Override
            public void ignoreRemove() {
                btnSheetFragment.dismiss();
            }
        });
        btnSheetFragment.show(getChildFragmentManager(), btnSheetFragment.getTag());

    }

    public interface onRemoveSubmited{
        void onRemove(int amount);
    }

}
