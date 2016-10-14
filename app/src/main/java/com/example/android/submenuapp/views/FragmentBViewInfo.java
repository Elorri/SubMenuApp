package com.example.android.submenuapp.views;

import android.util.Log;

import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public class FragmentBViewInfo implements AppWindowViewInfo {

    @Override
    public int getViewLayout() {
        return  R.layout.fragment_b_wrapper;
    }
    @Override
    public int getFragmentTagRes() {
        return R.string.fragment_b_tag;
    }
    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        Log.e("ff", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName+" "+FragmentCViewInfo.class.getName());
        Log.e("ff", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName.equals(FragmentCViewInfo.class.getName()));
        return currentLayoutName.equals(FragmentCViewInfo.class.getName());
    }

    @Override
    public String getListenerClassName() {
        return null;
    }


}
