package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action){
            openActionMenu();
            return true;
        }
        return false;
    }

    private void openActionMenu() {

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainFragment mainFragment=(MainFragment) getSupportFragmentManager().findFragmentById(R.id
                .main_fragment);
        mainFragment.onAttachedToWindow();

    }
}
