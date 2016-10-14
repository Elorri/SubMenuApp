package com.example.android.submenuapp.views;

import android.util.Log;

import com.example.android.submenuapp.MainFragment;
import com.example.android.submenuapp.R;
import com.example.android.submenuapp.window.AppWindowViewInfo;

/**
 * Created by Elorri on 13/10/2016.
 */
public class FragmentCViewInfo  implements AppWindowViewInfo {
    @Override
    public int getViewLayout() {
        return R.layout.fragment_c_wrapper;
    }
    @Override
    public int getFragmentTagRes() {
        return R.string.fragment_c_tag;
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
}
