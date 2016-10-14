package com.example.android.submenuapp.views;

import android.content.Context;
import android.os.Bundle;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by nebo-android2016 on 10/10/16.
 */
public class FragmentAViewInfo implements AppWindowViewInfo {

    @Override
    public int getViewLayout() {
        return R.layout.fragment_a_wrapper;
    }

    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        return false;
    }

    @Override
    public String getListenerClassName() {
        return null;
    }

    @Override
    public int getFragmentId() {
        return R.id.fragment_a_id;
    }

    @Override
    public Bundle getData() {
        return null;
    }

    @Override
    public void setContext(Context context) {
     }

    @Override
    public String getViewInfoClassName() {
        return FragmentAViewInfo.class.getName();
    }

}
