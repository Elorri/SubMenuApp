package com.example.android.submenuapp;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by nebo-android2016 on 10/10/16.
 */
public class MainFragment extends Fragment {
    private View mMainview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMainview =inflater.inflate(R.layout.main_fragment_layout, null);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"mMainview "+ mMainview);
        mMainview.findViewById(R.id.openPopup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopupFragment(view);
            }
        });
        return mMainview;
    }


//    public void openPopupFragment(View anchor, View mMainview) {
//        Log.e("",Thread.currentThread().getStackTrace()[2]+"");
//        PopupWindow pw = createPopupWindow();
//        FrameLayout popupWindowRootView = new FrameLayout(getContext()); //Found out I need this otherwise later fragment_container id is not found
//        pw.setContentView(popupWindowRootView);
//        attachFragmentToView(popupWindowRootView);
//        Rect viewLocation = locateView(anchor);
//        pw.showAtLocation(mMainview, Gravity.NO_GRAVITY, viewLocation.left, viewLocation.top);
//    }
//
//    private PopupWindow createPopupWindow() {
//        final PopupWindow pw = new PopupWindow();
//        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        // Next 3 lines closes the popup window when touch outside of it (= when looses focus)
//        pw.setOutsideTouchable(true);
//        pw.setFocusable(true);
//        pw.setBackgroundDrawable(new BitmapDrawable());
//        return pw;
//    }
//
//    private void attachFragmentToView(View popupRootView) {
//        popupRootView.inflate(getContext(), R.layout.fragment_layout_wrapper, null); //add fragment to main activity root
//    }




    public void openPopupFragment(View anchor) {
        View popupWindowView = View.inflate(getContext(), R.layout.fragment_layout_wrapper, null);
        PopupWindow pw = new PopupWindow();
        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setContentView(popupWindowView);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        Rect anchorLocation = locateView(anchor);
        pw.showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLocation.right , anchorLocation.top );
    }




    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            //Happens when the mMainview doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

}