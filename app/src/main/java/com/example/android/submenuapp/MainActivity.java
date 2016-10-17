package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.submenuapp.views.FragmentBViewInfo;
import com.example.android.submenuapp.window.AppWindowManager;

public class MainActivity extends AppCompatActivity {


    private AppWindowManager mAppWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mAppWindowManager = new AppWindowManager(getApplicationContext(), getSupportFragmentManager());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action) {
            View anchor=new View(getApplicationContext());
            item.setActionView(anchor);
            openActionMenu(anchor);
            return true;
        }
        return false;
    }

    private void openActionMenu(View anchor) {
        mAppWindowManager.openPopup(anchor, FragmentBViewInfo.class.getName(), null, null); //Want to open the view described by class FragmentBViewInfo
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id
                .main_fragment);
        mainFragment.onAttachedToWindow();

    }
}
