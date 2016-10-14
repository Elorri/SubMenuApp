package com.example.android.submenuapp.window;

import android.support.v4.app.Fragment;

/**
 * Created by Elorri on 13/10/2016.
 */
public class AppWindowFragment extends Fragment {

    protected Callback mCallback;

    //Use by MainFragment
    public interface Callback {
        void weAreOnFragmentC();
    }

    protected AppWindowManager mAppWindowManager;

    public void setAppWindowManager(AppWindowManager appWindowManager) {
        this.mAppWindowManager = appWindowManager;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
