package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.submenuapp.views.FragmentAViewInfo;
import com.example.android.submenuapp.views.FragmentBViewInfo;
import com.example.android.submenuapp.views.FragmentC;
import com.example.android.submenuapp.views.FragmentCTag2ViewInfo;
import com.example.android.submenuapp.window.AppWindowManager;

/**
 * Created by nebo-android2016 on 10/10/16.
 */
public class MainFragment extends Fragment implements FragmentC.Callback{


    private AppWindowManager mAppWindowManager;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppWindowManager = new AppWindowManager(getContext(), getFragmentManager());
        this.savedInstanceState = savedInstanceState;
        mAppWindowManager.addFragmentListener(MainFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, null);
        view.findViewById(R.id.openPopupA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppWindowManager.openPopup(view, FragmentAViewInfo.class.getName(), null, null);
            }
        });
        view.findViewById(R.id.openPopupB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppWindowManager.openPopup(view, FragmentBViewInfo.class.getName(), null, null); //Want to open the view described by class FragmentBViewInfo
            }
        });
        view.findViewById(R.id.openPopupC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppWindowManager.openPopup(view, FragmentCTag2ViewInfo.class.getName(), null, null);
            }
        });
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAppWindowManager.onSaveInstanceState(outState);
    }

    public void onAttachedToWindow() {
        mAppWindowManager.onRestaureInstanceState(savedInstanceState);
    }

    @Override
    public void weAreOnFragmentC() {
        Toast.makeText(getContext(), "Fragment C event catch by MainFragment Youhoo", Toast.LENGTH_SHORT).show();
    }
}