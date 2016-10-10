package com.example.android.submenuapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PopupFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_layout, container);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"view "+view);
        return view;
    }

    public View getPopupView() {
        return view;
    }
}
