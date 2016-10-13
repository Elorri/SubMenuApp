package com.example.android.submenuapp.window;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public interface AppWindowViewInfo {

    int getViewLayout();

    int getFragmentTagRes();

    boolean shareSamePopupAs(String currentLayoutName);
}
