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
 * Created by Elorri on 17/10/2016.
 */
public class FragmentHelp extends AppWindowFragment {
    private View view;
    private AppWindowManager mAppWindowManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_help, null);
    Log.e("Sub", Thread.currentThread().getStackTrace()[2]+"view "+view);
    view.findViewById(R.id.goToMore).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAppWindowManager.openPopup(v, FragmentMoreViewInfo.class.getName(), null, null);
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
