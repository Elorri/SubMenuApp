package com.example.android.submenuapp;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void openPopup(View view) {
        PopupWindow pw=openPopupWindow(view, null, null);
        FrameLayout popupWindowRootView=new FrameLayout(getApplicationContext());
        pw.setContentView(popupWindowRootView);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"view "+pw.getContentView());
        popupWindowRootView.addView(createView(popupWindowRootView));
        showPopup(pw, view, null, null);
    }

    private PopupWindow openPopupWindow(final View view, Integer x, Integer y) {
        final PopupWindow pw = new PopupWindow();
        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // Next 3 lines closes the popup window when touch outside of it (= when looses focus)
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        return pw;
    }

    private View createView(View popupRootView) {
        View fragmentContainer=View.inflate(getApplicationContext(), R.layout.fragment_layout_wrapper, (ViewGroup) popupRootView);
        PopupFragment popupFragment = new PopupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, popupFragment).commit();
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]+"view "+popupFragment.getPopupView());
        return fragmentContainer;
    }



    private void showPopup(PopupWindow pw, View view, Integer x, Integer y) {
        if (x == null && y == null) {
            //Show the popup over the anchor
            Rect viewLocation = locateView(view);
            pw.showAtLocation(view, Gravity.NO_GRAVITY, viewLocation.left, viewLocation.top);
        } else {
            //Show the popup where the screen touch happened
            pw.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
        }

    }

    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            //Happens when the view doesn't exist on screen anymore.
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
