package com.example.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle(this.getResources().getString(R.string.settings));

        if(findViewById(R.id.fl_setting)!=null){
            getSupportFragmentManager().beginTransaction().add(R.id.fl_setting,new SettingsFragment(this)).commit();
        }
    }

}