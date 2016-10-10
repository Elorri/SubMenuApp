package com.example.android.submenuapp;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class AppWindowManager {

    private final Context mContext;

    View currentView;

    public AppWindowManager(Context context) {
        this.mContext = context;
    }

    public void openLayout(View anchor, View popupView, FragmentManager fragmentManager) {
        openPopupFragment(anchor, popupView, fragmentManager);
    }

    public void openPopupFragment(View anchor, View contentView,  FragmentManager fragmentManager) {
        if (currentView == null||contentView.)
        {
            Log.e("Nebo",Thread.currentThread().getStackTrace()[2]+"");
            contentView = View.inflate(mContext, R.layout.fragment_layout_wrapper, null);
            currentView=contentView;
//            AppWindowFragment addBlockFragment =  fragmentManager.findFragmentById(R.id.add_block_fragment);
//            addBlockFragment.setCallback(this);
        }


        PopupWindow pw = new PopupWindow();
        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setContentView(currentView);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        Rect anchorLocation = DialogUtils.locateView(anchor);
        Log.e("Nebo",Thread.currentThread().getStackTrace()[2]+"");
        pw.showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLocation.right, anchorLocation.top);
        Log.e("Nebo",Thread.currentThread().getStackTrace()[2]+"");

    }
}
