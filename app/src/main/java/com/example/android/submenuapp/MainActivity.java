package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.submenuapp.views.FragmentBViewInfo;
import com.example.android.submenuapp.window.AppWindowManager;

public class MainActivity extends AppCompatActivity {


    private AppWindowManager mAppWindowManager;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        mAppWindowManager = new AppWindowManager(mainFragment.getContext(), getSupportFragmentManager());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("Sub", Thread.currentThread().getStackTrace()[2] + "");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action) {
            View anchor = new View(getApplicationContext());
            item.setActionView(anchor);
            openActionMenu(item.getActionView());
            return true;
        }
        return false;
    }

    private void openActionMenu(View anchor) {
            mAppWindowManager.openPopup(anchor, FragmentBViewInfo.class.getName(), null, null);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mainFragment.onAttachedToWindow(mAppWindowManager);
    }
}
