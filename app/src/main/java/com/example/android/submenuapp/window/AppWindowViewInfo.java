package com.example.android.submenuapp.window;

import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by nebo-android2016 on 11/10/16.
 */
public interface AppWindowViewInfo {

    int getViewLayout();

    //If the view contains a fragment the fragment should be removed in this method
    void clear();

    void setView(View currentView);

    void setFragmentManager(FragmentManager fragmentManager);
}
