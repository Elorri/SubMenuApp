package com.example.android.submenuapp.window;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public interface AppWindowViewInfo {

    int getViewLayout();

    boolean shareSamePopupAs(String currentLayoutName);

    String getListenerClassName();

    int getFragmentId();

    Bundle getData();

    void setContext(Context context);

    String getViewInfoClassName();
}
