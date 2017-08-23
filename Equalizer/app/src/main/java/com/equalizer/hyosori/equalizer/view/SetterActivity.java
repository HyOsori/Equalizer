package com.equalizer.hyosori.equalizer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.equalizer.hyosori.equalizer.R;


public class SetterActivity extends AppCompatActivity implements SetterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setter);
    }
}
