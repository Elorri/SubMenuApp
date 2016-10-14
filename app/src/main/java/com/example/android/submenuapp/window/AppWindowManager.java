package com.example.android.submenuapp.window;

import android.content.Context;
import android.content.res.Resources;
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
    private final FragmentManager mFragmentManager;
    private final Resources mResources;

    private PopupWindow popupWindow;
    private FrameLayout containerView;

    //Needed for restauration
    private String currentLayoutName;
    private int lastWindowXposition;
    private int lastWindowYposition;

    // Because contentView that contains fragments won't be cleared up completely (we tried several
    // things like adding fragment dynamically and use fm.remove but with no luck, fragment
    // instance stay alive and we get a duplicate fragment crash) we will keep record of all
    // instantiated contentView and reuse them as needed.
    Map<String, View> contentViews = new HashMap<>();

    public AppWindowManager(Context context, FragmentManager mFragmentManager) {
        this.mContext = context;
        this.mResources = mContext.getResources();
        this.mFragmentManager = mFragmentManager;
        containerView = new FrameLayout(mContext);
    }

    /**
     * Open a popup with the layout referred by th contentLayoutName. If the popup window is
     * already opened and the layout requested is a parent or child layout of the current layout,
     * the layout requested is displayed in the same popup and a transition is made.
     * In other cases a new popup is opened.
     *
     * @param anchor
     * @param contentLayoutName
     * @param x                 if null will display window on the anchor starting at the top/left point
     * @param y                 if null will display window on the anchor starting at the top/left point
     */
    public void openPopup(View anchor, String contentLayoutName, Integer x, Integer y) {
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
            openInNewWindow(anchor, currentView, x, y);
            return;
        }
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "will openInSameWindow");
        currentView = getCurrentView(contentLayoutName, contentViewInfoInstance);
        currentLayoutName = contentLayoutName;
        openInSameWindow(anchor, currentView, x, y);
    }

    private View getCurrentView(String contentLayoutName, AppWindowViewInfo contentViewInfoInstance) {
        View currentView = contentViews.get(contentLayoutName);
        if (currentView == null) {
            currentView = View.inflate(mContext, contentViewInfoInstance.getViewLayout(), null);
            currentView.setTag(R.id.window_id, contentViewInfoInstance.getClass().getName());
            contentViews.put(contentViewInfoInstance.getClass().getName(), currentView);
            String fragmentTag = mContext.getResources().getString(contentViewInfoInstance.getFragmentTagRes());
            if (fragmentTag != null) {
                AppWindowFragment fragment = (AppWindowFragment) mFragmentManager.findFragmentByTag(fragmentTag);
                fragment.setmAppWindowManager(this);
            }
        }
        return currentView;
    }

    private AppWindowViewInfo createContentViewInfoInstance(String contentViewName) {
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "contentViewName " + contentViewName);
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

    private void openInSameWindow(View anchor, View currentView, Integer x, Integer y) {
        containerView.removeAllViews();
        containerView.addView(currentView);
        if (!popupWindow.isShowing()) {
            show(popupWindow, anchor, x, y);
        }
    }

    private void openInNewWindow(View anchor, View currentView, Integer x, Integer y) {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        containerView.removeAllViews();
        containerView.addView(currentView);
        popupWindow.setContentView(containerView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        show(popupWindow, anchor, x, y);
    }

    private void show(PopupWindow popupWindow, View anchor, Integer x, Integer y) {
        Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "popupWindow " + popupWindow + " anchor " + anchor + "x " + x + " y " + y);
        //For some reason default Cardview add space and display the second dialog windows in cascade. We don't want that => we remove that.
        int horizontalSpace = (int) mResources.getDimension(R.dimen.dialog_horizontal_space);
        int verticalSpace = (int) mResources.getDimension(R.dimen.dialog_vertical_space);

        if (x == null && y == null) {
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "x y null");
            //Show the popup over the anchor
            Rect viewLocation = DialogUtils.locateView(anchor);
            x = viewLocation.left;
            y = viewLocation.top;
        }
        lastWindowXposition=x;
        lastWindowYposition=y;
        popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x - horizontalSpace, y - verticalSpace);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (popupWindow != null) {
            Bundle windowManagerBundle = new Bundle();
            windowManagerBundle.putString(mContext.getResources().getString(R.string.current_layout_name), currentLayoutName);
            windowManagerBundle.putInt(mContext.getResources().getString(R.string.last_window_x_position), lastWindowXposition);
            windowManagerBundle.putInt(mContext.getResources().getString(R.string.last_window_y_position), lastWindowYposition);
            Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isShown " + popupWindow.isShowing());
            windowManagerBundle.putBoolean(mContext.getResources().getString(R.string.is_last_window_shown), popupWindow.isShowing());
            popupWindow.dismiss(); //to avoid leakWindow crash. (Note need to be called after popupWindow.isShowing to remember correctly if the window was opened)
            outState.putBundle(mContext.getResources().getString(R.string.window_manager_bundle), windowManagerBundle);
        }
    }

    public void onRestaureInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle windowManagerBundle = savedInstanceState.getBundle(mContext.getResources().getString(R.string.window_manager_bundle));
            if (windowManagerBundle != null) {
                currentLayoutName = windowManagerBundle.getString(mContext.getResources().getString(R.string.current_layout_name));
                lastWindowXposition = windowManagerBundle.getInt(mContext.getResources().getString(R.string.last_window_x_position));
                lastWindowYposition = windowManagerBundle.getInt(mContext.getResources().getString(R.string.last_window_y_position));
                boolean isShown = windowManagerBundle.getBoolean(mContext.getResources().getString(R.string.is_last_window_shown));
                Log.e("Nebo", Thread.currentThread().getStackTrace()[2] + "isShown " + isShown);
                if (isShown) {
                    openPopup(new View(mContext), currentLayoutName, lastWindowXposition, lastWindowYposition);
                }
            }
        }
    }
}
