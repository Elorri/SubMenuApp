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

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public class FragmentB extends AppWindowFragment {
    private View view;

    private AppWindowManager mAppWindowManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_b, null);
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+"view "+view);
        view.findViewById(R.id.goToC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppWindowManager.openPopup(v, FragmentCTag1ViewInfo.class.getName(), null, null);
            }
        });
        return view;
    }


    @Override
    public void setAppWindowManager(AppWindowManager appWindowManager) {
        this.mAppWindowManager = appWindowManager;
    }

    @Override
    public void setCallback(Callback callback) {

    }

    @Override
    public void setData(Bundle data) {

    }
}
