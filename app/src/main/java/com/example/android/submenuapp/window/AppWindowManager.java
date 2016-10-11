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

import java.util.HashMap;
import java.util.Map;

public class AppWindowManager {

    private final Context mContext;


    private PopupWindow pw;

    private AppWindowViewInfo currentViewInfoInstance;

    // Because contentView that contains fragments won't be cleared up completely (we tried several
    // things like adding fragment dynamically and use fm.remove but with no luck, fragment
    // instance stay alive and we get a duplicate fragment crash) we will keep record of all
    // instantiated contentView and reuse them as needed.
    Map<String, View> contentViews = new HashMap<>();

    


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

        View currentView = contentViews.get(contentViewName);
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2]
                + "contentViews.get(contentViewName) " + contentViews.get(contentViewName));
        if (currentView == null) {
            currentView = View.inflate(mContext, contentViewInfoInstance.getViewLayout(), null);
            currentView.setTag(R.id.window_id, contentViewInfoInstance.getClass().getName());
            contentViews.put(contentViewInfoInstance.getClass().getName(), currentView);
            currentViewInfoInstance = contentViewInfoInstance;
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2]
                    + "currentView " + currentView + "set tag " + contentViewInfoInstance.getClass().getName());
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
}
