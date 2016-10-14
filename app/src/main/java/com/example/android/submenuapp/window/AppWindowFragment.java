package com.example.android.submenuapp.window;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class AppWindowFragment extends Fragment {

    protected Callback mCallback;
    protected Bundle mData;

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

    public abstract void setData(Bundle data) ;
}
