package com.soroush.protrial.userpanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.soroush.protrial.R;

public class RemoveConfirmation extends BottomSheetDialogFragment {

    private onConfirmationRemove listener;
    public RemoveConfirmation(onConfirmationRemove listener){
        this.listener = listener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton mbYes = view.findViewById(R.id.mb_yes_remove);
        MaterialButton mbNo = view.findViewById(R.id.mb_no_remove);

        mbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.confRemove();
            }});

        mbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ignoreRemove();
            }});

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.confirmation_remove, container, false);
    }

    public interface onConfirmationRemove{
        void confRemove();
        void ignoreRemove();
    }
}
