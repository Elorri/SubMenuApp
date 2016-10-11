package com.example.android.submenuapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainFragment mainFragment=(MainFragment) getSupportFragmentManager().findFragmentById(R.id
                .main_fragment);
        mainFragment.onAttachedToWindow();

    }
}
