package com.net.santosh.customnavigationdrawer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.net.santosh.customnavigationdrawer.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setTitle("AboutUs");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    }
}
