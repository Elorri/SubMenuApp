package com.example.android.submenuapp.views;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public class FragmentBViewInfo implements AppWindowViewInfo {

    View view;
    FragmentManager fragmentManager;

    @Override
    public int getViewLayout() {
        return  R.layout.fragment_a_wrapper;
    }

    @Override
    public void setView(View currentView) {
        this.view = currentView;
    }

    @Override
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void clear() {
        PopupFragmentB popupFragmentB = (PopupFragmentB) fragmentManager.findFragmentById(R.id.fragment_b_id);
        fragmentManager.beginTransaction().remove(popupFragmentB).commit();
        //popupFragmentA.setCallback(this);
    }

}
