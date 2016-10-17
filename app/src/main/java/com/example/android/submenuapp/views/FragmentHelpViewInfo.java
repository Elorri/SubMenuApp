package com.example.android.submenuapp.views;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.android.submenuapp.MainFragment;
import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by Elorri on 17/10/2016.
 */
public class FragmentHelpViewInfo implements AppWindowViewInfo {

    private Resources mResources;

    @Override
    public void setContext(Context context) {
        mResources=context.getResources();
    }

    @Override
    public int getViewLayout() {
        return R.layout.fragment_help_wrapper;
    }

    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName+" "+FragmentBViewInfo.class.getName());
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName.equals(FragmentBViewInfo.class.getName()));
        return currentLayoutName.equals(FragmentMoreViewInfo.class.getName());
    }

    @Override
    public String getListenerClassName() {
        return MainFragment.class.getName();
    }

    @Override
    public int getFragmentId() {
        return R.id.fragment_help_id;
    }

    @Override
    public Bundle getData() {
        return null;
    }


    @Override
    public String getViewInfoClassName() {
        //We gives the same name for FragmentCTag1ViewInfo and FragmentCTag2ViewInfo because they share the same fragment
        return FragmentHelpViewInfo.class.getName();
    }
}