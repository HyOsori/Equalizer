package com.equalizer.hyosori.equalizer.view;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.equalizer.hyosori.equalizer.R;
import com.equalizer.hyosori.equalizer.presenter.SetterPresenter;


public class SetterActivity extends AppCompatActivity implements SetterView {

    private Toolbar toolbar;
    private ConstraintLayout seekBars;
    private Button applyBtn;
    private Spinner baseSpinner;
    private Spinner targetSpinner;

    SetterPresenter presenter = new SetterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setter);

        applyBtn = (Button) findViewById(R.id.btn_apply);
        seekBars = (ConstraintLayout) findViewById(R.id.seekBarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        baseSpinner = (Spinner) findViewById(R.id.spinner1);
        targetSpinner = (Spinner) findViewById(R.id.spinner2);

        setSupportActionBar(toolbar);

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

        baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = seekBars.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = seekBars.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        targetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = seekBars.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = seekBars.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_new) {
            startActivity(new Intent(SetterActivity.this, GetterActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
