package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.submenuapp.views.FragmentAViewInfo;
import com.example.android.submenuapp.views.FragmentBViewInfo;
import com.example.android.submenuapp.window.AppWindowManager;

/**
 * Created by nebo-android2016 on 10/10/16.
 */
public class MainFragment extends Fragment {


    private AppWindowManager mWindowManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindowManager = new AppWindowManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout, null);
        view.findViewById(R.id.openPopupA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWindowManager.openLayout(view, FragmentAViewInfo.class.getName(), getFragmentManager());
            }
        });
        view.findViewById(R.id.openPopupB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWindowManager.openLayout(view, FragmentBViewInfo.class.getName(), getFragmentManager());
            }
        });
        return view;
    }





}