package com.example.android.submenuapp.window;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public interface AppWindowViewInfo {

    int getViewLayout();

    boolean shareSamePopupAs(String currentLayoutName);

    String getListenerClassName();

    int getFragmentId();

    int getFragmentTagRes();
}
