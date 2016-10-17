package com.example.android.submenuapp.views;

import android.content.Context;
import android.os.Bundle;
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
    public boolean shareSamePopupAs(String currentLayoutName) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName+" "+FragmentCTag1ViewInfo.class.getName());
        Log.e("Sub", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName.equals(FragmentCTag1ViewInfo.class.getName()));
        return currentLayoutName.equals(FragmentCTag1ViewInfo.class.getName())||currentLayoutName.equals(FragmentCTag2ViewInfo.class.getName());
    }

    @Override
    public String getListenerClassName() {
        return null;
    }

    @Override
    public int getFragmentId() {
        return R.id.fragment_b_id;
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
        return FragmentBViewInfo.class.getName();
    }


}
