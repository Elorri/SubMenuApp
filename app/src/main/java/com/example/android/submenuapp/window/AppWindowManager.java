package com.example.android.submenuapp.window;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.example.android.submenuapp.DialogUtils;
import com.example.android.submenuapp.R;

import java.util.HashMap;
import java.util.Map;

public class AppWindowManager {

    private final Context mContext;
    private final FragmentManager fragmentManager;


    private PopupWindow popupWindow;
    private FrameLayout containerView;

    private String currentLayoutName;

    // Because contentView that contains fragments won't be cleared up completely (we tried several
    // things like adding fragment dynamically and use fm.remove but with no luck, fragment
    // instance stay alive and we get a duplicate fragment crash) we will keep record of all
    // instantiated contentView and reuse them as needed.
    Map<String, View> contentViews = new HashMap<>();


    public AppWindowManager(Context context, FragmentManager fragmentManager) {
        this.mContext = context;
        this.fragmentManager = fragmentManager;
        containerView = new FrameLayout(mContext);
    }


    /**
     * Open a popup with the layout referred by th contentLayoutName. If the popup window is
     * already opened and the layout requested is a parent or child layout of the current layout,
     * the layout requested is displayed in the same popup and a transition is made.
     * In other cases a new popup is opened.
     */
    public void openPopup(View anchor, String contentLayoutName) {
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "popupWindow " + popupWindow);
        if (popupWindow != null)
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "popupWindow isShowing " + popupWindow.isShowing());

        AppWindowViewInfo contentViewInfoInstance = createContentViewInfoInstance(contentLayoutName);
        if (contentViewInfoInstance == null) {
            return;
        }
        View currentView;
        if ((currentLayoutName == null)
                || (!contentViewInfoInstance.shareSamePopupAs(currentLayoutName))) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "will openInNewWindow");
            currentView = getCurrentView(contentLayoutName, contentViewInfoInstance);
            currentLayoutName = contentLayoutName;
            openInNewWindow(anchor, currentView);
            return;
        }
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "will openInSameWindow");
        currentView = getCurrentView(contentLayoutName, contentViewInfoInstance);
        currentLayoutName = contentLayoutName;
        openInSameWindow(anchor, currentView);
    }

    private View getCurrentView(String contentLayoutName, AppWindowViewInfo contentViewInfoInstance) {
        View currentView = contentViews.get(contentLayoutName);
        if (currentView == null) {
            currentView = View.inflate(mContext, contentViewInfoInstance.getViewLayout(), null);
            currentView.setTag(R.id.window_id, contentViewInfoInstance.getClass().getName());
            contentViews.put(contentViewInfoInstance.getClass().getName(), currentView);
            String fragmentTag = mContext.getResources().getString(contentViewInfoInstance.getFragmentTagRes());
            if (fragmentTag != null) {
                AppWindowFragment fragment = (AppWindowFragment) fragmentManager.findFragmentByTag(fragmentTag);
                fragment.setmAppWindowManager(this);
            }
        }
        return currentView;
    }


    private AppWindowViewInfo createContentViewInfoInstance(String contentViewName) {
        try {
            return (AppWindowViewInfo) Class.forName(contentViewName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void openInSameWindow(View anchor, View currentView) {
        containerView.removeAllViews();
        containerView.addView(currentView);
        if (!popupWindow.isShowing()) {
            show(popupWindow, anchor);
        }
    }

    private void openInNewWindow(View anchor, View currentView) {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        containerView.removeAllViews();
        containerView.addView(currentView);
        popupWindow.setContentView(containerView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        show(popupWindow, anchor);
    }

    private void show(PopupWindow popupWindow, View anchor) {
        Rect anchorLocation = DialogUtils.locateView(anchor);
        popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLocation.right, anchorLocation.top);
    }


    public void onSaveInstanceState(Bundle outState) {
        if (popupWindow != null) {
            popupWindow.dismiss(); //to avoid leakWindow crash
        }
        outState.putString(mContext.getResources().getString(
                R.string.current_layout_name), currentLayoutName);
    }

    public void onRestaureInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentLayoutName = savedInstanceState.getString(mContext.getResources().getString(
                    R.string.current_layout_name));
            openPopup(new View(mContext), currentLayoutName);
        }
    }
}
