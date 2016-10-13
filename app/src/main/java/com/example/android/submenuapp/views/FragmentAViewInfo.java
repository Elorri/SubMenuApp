package com.example.android.submenuapp.views;

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
    public int getFragmentTagRes() {
        return R.string.fragment_a_tag;
    }

    @Override
    public boolean shareSamePopupAs(String currentLayoutName) {
        return false;
    }

}
