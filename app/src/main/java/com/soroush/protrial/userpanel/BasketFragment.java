package com.soroush.protrial.userpanel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soroush.protrial.App;
import com.soroush.protrial.AppDatabase;
import com.soroush.protrial.BrandAdapter;
import com.soroush.protrial.BrandModel;
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
                totalPrice += Integer.parseInt(model.getDesc());
            }

        } else {
            fabPay.hide();
        }
        fabPay.setText(getResources().getText(R.string.payment));
        fabPay.append(" - (" + totalPrice + "$)");

    }

    private void setUpRecycler() {

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(context));

        db = new AppDatabase(context);
        list = db.getAllBasket(App.SAMSUNG_TABLE);
        adapter = new OrderAdapter(list, this);
        recycler.setAdapter(adapter);
        db.close();

    }

    private void backArrowSetup(){

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                Fragment fragment = new TvFragment(context, (BrandAdapter.onClickItemListener) context);
                fTransaction.setCustomAnimations(R.anim.right_in, R.anim.right_out);
                fTransaction.replace(R.id.panel_container, fragment);
                fTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fTransaction.commit();
            }
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
                db.updateBasket(App.SAMSUNG_TABLE, model.getId(), 0);
                list.remove(model);
                adapter.notifyDataSetChanged();
                updatePrice();
                listener.onRemove();
                btnSheetFragment.dismiss();
            }

            @Override
            public void ignoreRemove() {
                btnSheetFragment.dismiss();
            }
        });
        btnSheetFragment.show(getFragmentManager(), btnSheetFragment.getTag());

    }

    public interface onRemoveSubmited{
        void onRemove();
    }

}
