package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        MenuItem share = menu.findItem(R.id.action_share);
        if (share == null) {
            menu.add(Menu.NONE, R.id.action_share, 2, getString(R.string.share));
        }
        MenuItem imported = menu.findItem(R.id.action_import);
        if (imported == null) {
            menu.add(Menu.NONE, R.id.action_import, 1, getString(R.string.imported));
        }
        SubMenu submenu = menu.getItem(R.id.action_import).getSubMenu();
        submenu.addSubMenu(0,Menu.NONE, 0, "submenu item" );
        return super.onPrepareOptionsPanel(view, menu);
    }


//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate your main_menu into the menu
//        getMenuInflater().inflate(R.menu.menu_details, menu);
//
//        // Find the menuItem to add your SubMenu
//        MenuItem myMenuItem = menu.findItem(R.id.action_import);
//
//        // Inflating the sub_menu menu this way, will add its menu items
//        // to the empty SubMenu you created in the xml
//        getMenuInflater().inflate(R.menu.sub_menu, myMenuItem.getSubMenu());
//        return true;
//
//    }

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
}
