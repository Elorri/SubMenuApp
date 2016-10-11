package com.example.android.submenuapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.submenuapp.R;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public class PopupFragmentB extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_b, null);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"view "+view);
        return view;
    }

}
