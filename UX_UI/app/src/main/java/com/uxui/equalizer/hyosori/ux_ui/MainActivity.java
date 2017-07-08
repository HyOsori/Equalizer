package com.uxui.equalizer.hyosori.ux_ui;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout seekBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBars = (ConstraintLayout) findViewById(R.id.seekBarLayout);

        Button applyBtn = (Button) findViewById(R.id.btn_apply);
        applyBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int childCount = seekBars.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = seekBars.getChildAt(i);
                    v.setEnabled(true);
                }
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = seekBars.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = seekBars.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = seekBars.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = seekBars.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
