package com.net.santosh.customnavigationdrawer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.net.santosh.customnavigationdrawer.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    }
}
