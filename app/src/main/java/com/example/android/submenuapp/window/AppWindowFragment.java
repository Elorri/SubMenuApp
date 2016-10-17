package com.example.android.submenuapp.window;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class AppWindowFragment extends Fragment {

    public interface Callback {}

    public abstract void setAppWindowManager(AppWindowManager appWindowManager) ;

    public abstract void setCallback(Callback callback);

    public abstract void setData(Bundle data) ;
}
