package com.example.android.submenuapp.window;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
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


    //To restaure behavior of the app correctly on rotation. App manager need to know all the listeners of the fragments created.
    Map<String, AppWindowFragment.Callback> fragmentListeners = new HashMap<>();


    public AppWindowManager(Context context, FragmentManager mFragmentManager) {
        this.mContext = context;
        this.mResources = mContext.getResources();
        this.mFragmentManager = mFragmentManager;
        containerView = new FrameLayout(mContext);
    }

    /**
     * Open a popup with the layout referred by the contentLayoutName param. If the popup window is
     * already opened and the layout requested is a parent or child layout of the current layout,
     * the layout requested is displayed in the same popup and a transition is made.
     * In other cases a new popup is opened.
     *
     * if the layout contains a fragment and this fragment has a listener
     *   - use addFragmentListener() before openPopup call.
     *   - override corresponding fragmentViewInfo class getListenerClassName() with listener class name
     *
     * @param anchor
     * @param contentLayoutName should be the name of a class implementing AppWindowViewInfo
     * @param x                 if null will display window on the anchor starting at the top/left point
     * @param y                 if null will display window on the anchor starting at the top/left point
     */
    public void openPopup(View anchor, String contentLayoutName, Integer x, Integer y) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "popupWindow " + popupWindow);
        if (popupWindow != null)
            Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "popupWindow isShowing " + popupWindow.isShowing());

        AppWindowViewInfo contentViewInfoInstance = createContentViewInfoInstance(contentLayoutName);
        if (contentViewInfoInstance == null) {
            return;
        }
        View currentView;
        if ((currentLayoutName == null)
                || (!contentViewInfoInstance.shareSamePopupAs(currentLayoutName))) {
            Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "will openInNewWindow");
            currentView = getCurrentView(contentLayoutName, contentViewInfoInstance);
            currentLayoutName = contentLayoutName;
            openInNewWindow(anchor, currentView, x, y);
            return;
        }
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "will openInSameWindow");
        currentView = getCurrentView(contentLayoutName, contentViewInfoInstance);
        currentLayoutName = contentLayoutName;
        openInSameWindow(anchor, currentView, x, y);
    }

    private View getCurrentView(String contentLayoutName, AppWindowViewInfo contentViewInfoInstance) {
        //We can't put contentLayoutName directly here because in some case we want to give the name of the parent class.
        //That's why we use the method getViewInfoClassName
        View currentView = contentViews.get(contentViewInfoInstance.getViewInfoClassName());
        if (currentView == null) {
            currentView = View.inflate(mContext, contentViewInfoInstance.getViewLayout(), null);
            contentViews.put(contentViewInfoInstance.getViewInfoClassName(), currentView);
        }
        //String fragmentTag = mContext.getResources().getString(contentViewInfoInstance.getFragmentTagRes());
        int fragmentId=contentViewInfoInstance.getFragmentId();
        if (fragmentId != 0) { //If the view contain a fragment we give him an instance of AppWindowManager in case this fragment needs to open windows
            AppWindowFragment fragment = (AppWindowFragment) mFragmentManager.findFragmentById(fragmentId);
            fragment.setAppWindowManager(this);
            String listenerClassName = contentViewInfoInstance.getListenerClassName();
            if (listenerClassName != null) { //If the fragment wants to communicate with other class we set a callback here
                AppWindowFragment.Callback callback=fragmentListeners.get(listenerClassName);
                fragment.setCallback(callback);
            }
            Bundle data = contentViewInfoInstance.getData();
            if (data != null) { //If the fragment need other data
                fragment.setData(data);
            }
        }
        return currentView;
    }

    private AppWindowViewInfo createContentViewInfoInstance(String contentViewName) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "contentViewName " + contentViewName);
        try {
            AppWindowViewInfo contentViewInfoInstance=(AppWindowViewInfo) Class.forName(contentViewName).newInstance();
            contentViewInfoInstance.setContext(mContext); //instance need context to access ressources
            return contentViewInfoInstance;
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
//        containerView.removeAllViews();
//        containerView.addView(currentView);
        setWindowView(containerView, currentView);
        if (!popupWindow.isShowing()) {
            show(popupWindow, anchor, x, y);
        }
    }

    private void openInNewWindow(View anchor, View currentView, Integer x, Integer y) {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        containerView.removeAllViews();
//        containerView.addView(currentView);
        popupWindow.setContentView(containerView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        setWindowView(containerView, currentView);
        show(popupWindow, anchor, x, y);
    }

    private void setWindowView(FrameLayout container, View finalView) {
        Scene finalScene = new Scene(container, finalView);
        finalScene.enter(); //Calling this will make final scene text appearing on
        // before container has finished resizing.
        final Transition t = new AutoTransition();
        t.setDuration(500);
        TransitionManager.go(finalScene, t);//This will make the container.addView no
        // need to do it ourselves
    }

    private void show(PopupWindow popupWindow, View anchor, Integer x, Integer y) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "popupWindow " + popupWindow + " anchor " + anchor + "x " + x + " y " + y);
        //For some reason default Cardview add space and display the second dialog windows in cascade. We don't want that => we remove that.
        int horizontalSpace = (int) mResources.getDimension(R.dimen.dialog_horizontal_space);
        int verticalSpace = (int) mResources.getDimension(R.dimen.dialog_vertical_space);

        if (x == null && y == null) {
            Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "x y null");
            //Show the popup over the anchor
            Rect viewLocation = DialogUtils.locateView(anchor);
            x = viewLocation.left;
            y = viewLocation.top;
        }
        lastWindowXposition = x;
        lastWindowYposition = y;
        popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x - horizontalSpace, y - verticalSpace);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (popupWindow != null) {
            Bundle windowManagerBundle = new Bundle();
            windowManagerBundle.putString(mContext.getResources().getString(R.string.current_layout_name), currentLayoutName);
            windowManagerBundle.putInt(mContext.getResources().getString(R.string.last_window_x_position), lastWindowXposition);
            windowManagerBundle.putInt(mContext.getResources().getString(R.string.last_window_y_position), lastWindowYposition);
            Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "isShown " + popupWindow.isShowing());
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
                Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "isShown " + isShown);
                if (isShown) {
                    openPopup(new View(mContext), currentLayoutName, lastWindowXposition, lastWindowYposition);
                }
            }
        }
    }

    public void addFragmentListener(AppWindowFragment.Callback listener) {
        fragmentListeners.put(listener.getClass().getName(), listener);
    }
}
