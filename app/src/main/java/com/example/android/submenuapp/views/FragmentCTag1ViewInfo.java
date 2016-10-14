package com.example.android.submenuapp.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.android.submenuapp.MainFragment;
import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by Elorri on 13/10/2016.
 */
public class FragmentCTag1ViewInfo implements AppWindowViewInfo {

    private Context mContext;

    @Override
    public int getViewLayout() {
        return R.layout.fragment_c_wrapper;
    }

    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        Log.e("ff", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName+" "+FragmentBViewInfo.class.getName());
        Log.e("ff", Thread.currentThread().getStackTrace()[2]+""+currentLayoutName.equals(FragmentBViewInfo.class.getName()));
        return currentLayoutName.equals(FragmentBViewInfo.class.getName());
    }

    @Override
    public String getListenerClassName() {
        return MainFragment.class.getName();
    }

    @Override
    public int getFragmentId() {
        return R.id.fragment_c_id;
    }

    @Override
    public Bundle getData() {
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"");
        return null;
    }

    @Override
    public void setContext(Context context) {
mContext=context;
    }

    @Override
    public String getViewInfoClassName() {
        //We gives the same name for FragmentCTag1ViewInfo and FragmentCTag2ViewInfo because they share the same fragment
        return mContext.getResources().getString(R.string.fragment_c_view_info);
    }
}
