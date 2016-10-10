package com.example.android.submenuapp;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private static final int MENU3 = 1;
    private static final int MENU4 = 2;
    private static final int SUBMENU1 = 3;
    private static final int SUBMENU2 = 4;
    private static final int SUBMENU3 = 5;
    private static final int GROUP1 = 6;
    private static final int MENU5 = 7;
    private static final int MENU6 = 8;
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView=findViewById(R.id.main_content);
        //openAddBlockPopupWindow(mainView, 200, 200);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        Log.w("ANDROID MENU TUTORIAL:", "onPrepareOptionsMenu(Menu menu)");
        MenuItem menu5 = menu.findItem(MENU5);
        if(menu5 == null){
            menu5 = menu.add(Menu.NONE, MENU5, 5, "Menu No. 5");
        }
        MenuItem menu6 = menu.findItem(MENU6);
        if(menu6 == null){
            menu6 = menu.add(Menu.NONE, MENU6, 5, "Menu No. 6");
        }

        MenuItem menu2 = menu.findItem(R.id.menu2);
        SubMenu subMenu2 = menu2.getSubMenu();

        MenuItem subMenuItem3 = menu.findItem(SUBMENU3);
        if(subMenuItem3 == null){
            subMenu2.add(R.id.group2, SUBMENU3, 3, "SubMenu No. 3");
            subMenu2.setGroupCheckable(R.id.group2,true,true);
        }

        return true;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        Log.w("ANDROID MENU TUTORIAL:", "onCreateOptionsMenu(Menu menu)");

        MenuItem menu3 = menu.add(Menu.NONE, MENU3, 3, "Menu No. 3");
        menu3.setAlphabeticShortcut('c');

        SubMenu menu4 = menu.addSubMenu(Menu.NONE, MENU4, 4,"Menu No. 4");
        menu4.add(GROUP1, SUBMENU1, 1, "SubMenu No. 1");
        menu4.add(GROUP1, SUBMENU2, 2, "SubMenu No. 2");
        menu4.setGroupCheckable(GROUP1,true,true);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Utils.log(getClass().getSimpleName());
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    private void openAddBlockPopupWindow(final View view, int x, int y)
    {

//        MenuBuilder builder = new MenuBuilder(this);
//        builder.getMenuView()

        ListMenuPresenter presenter = new ListMenuPresenter(this, R.menu.optionsmenu);
        MenuView popupView = presenter.getMenuView((ViewGroup) mainView);

        //We do this check otherwise we get a duplicate AddBlockFragment problem. AddBlockFragment can't be created several times.
//        Menu menuView = getMenuInflater().inflate(R.menu.optionsmenu, new MenuBuilder(this));
//            AddBlockFragment addBlockFragment = (AddBlockFragment) getFragmentManager().findFragmentById(R.id.add_block_fragment);
//            addBlockFragment.setCallback(this);

        final PopupWindow pw = new PopupWindow();
        pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setContentView((View) popupView);
        // Next 3 lines closes the popup window when touch outside of it (= when looses focus)
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        //Show the popup where the screen touch happened
        pw.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
    }
}
