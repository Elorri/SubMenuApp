package com.example.android.submenuapp.views;

import android.content.Context;
import android.os.Bundle;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by Elorri on 17/10/2016.
 */
public class FragmentMoreViewInfo implements AppWindowViewInfo {
    @Override
    public int getViewLayout() {
        return R.layout.fragment_more_wrapper;
    }

    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        return currentLayoutName.equals(FragmentHelpViewInfo.class.getName());
    }

    @Override
    public String getListenerClassName() {
        return null;
    }

    @Override
    public int getFragmentId() {
        return R.id.fragment_more_id;
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