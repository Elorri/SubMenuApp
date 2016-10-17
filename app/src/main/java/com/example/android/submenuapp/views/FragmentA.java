package com.example.android.submenuapp.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowFragment;
import com.example.android.submenuapp.window.AppWindowManager;

public class FragmentA extends AppWindowFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_a, null);
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+"view "+view);
        return view;
    }

    @Override
    public void setAppWindowManager(AppWindowManager appWindowManager) {

    }

    @Override
    public void setCallback(Callback callback) {

    }

    @Override
    public void setData(Bundle data) {

    }
}
