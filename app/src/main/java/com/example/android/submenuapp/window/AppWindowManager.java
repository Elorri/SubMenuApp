package com.example.android.submenuapp.window;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.android.submenuapp.DialogUtils;
import com.example.android.submenuapp.R;

public class AppWindowManager {

    private final Context mContext;


    private PopupWindow pw;

    private AppWindowViewInfo currentViewInfoInstance;
    View currentView;


    public AppWindowManager(Context context) {
        this.mContext = context;
    }

    public void openLayout(View anchor, String popupLayoutName, FragmentManager fragmentManager) {
        openPopupFragment(anchor, popupLayoutName, fragmentManager);
    }

    public void openPopupFragment(View anchor, String contentViewName, FragmentManager fragmentManager) {
        AppWindowViewInfo contentViewInfoInstance = null;
        try {
            contentViewInfoInstance = (AppWindowViewInfo) Class.forName(contentViewName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (currentView == null) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "currentView " + currentView);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "contentViewName " + contentViewName);
            inflate(contentViewInfoInstance, fragmentManager);
        } else if (currentView.getTag(R.id.window_id) == null) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "currentView " + currentView);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "currentView.getTag() " + currentView.getTag(R.id.window_id));
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "contentViewName " + contentViewName);
            inflate(contentViewInfoInstance, fragmentManager);
        } else if (!contentViewName.equals(currentView.getTag(R.id.window_id))) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "currentView.getTag() " + currentView.getTag(R.id.window_id));
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "contentViewName " + contentViewName);
            inflate(contentViewInfoInstance, fragmentManager);
        }

        pw = new PopupWindow();
        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setContentView(currentView);
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        Rect anchorLocation = DialogUtils.locateView(anchor);
        pw.showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLocation.right, anchorLocation.top);
    }

    private void inflate(AppWindowViewInfo contentViewInfoInstance, FragmentManager fragmentManager) {
        if (currentViewInfoInstance != null) {
            currentViewInfoInstance.clear();
        }

        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "inflate "+contentViewInfoInstance);
        currentView = View.inflate(mContext, contentViewInfoInstance.getViewLayout(), null);
        currentView.setTag(R.id.window_id, contentViewInfoInstance.getClass().getName());
        contentViewInfoInstance.setView(currentView);
        contentViewInfoInstance.setFragmentManager(fragmentManager);
        currentViewInfoInstance = contentViewInfoInstance;
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "currentView " + currentView + "set tag " + contentViewInfoInstance.getClass().getName());

    }
}
