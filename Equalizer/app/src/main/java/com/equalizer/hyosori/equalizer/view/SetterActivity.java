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
import android.widget.SeekBar;
import android.widget.Spinner;

import com.equalizer.hyosori.equalizer.R;
import com.equalizer.hyosori.equalizer.presenter.SetterPresenter;


public class SetterActivity extends AppCompatActivity implements SetterView {

    private Toolbar toolbar;
    private ConstraintLayout detailSettingLayout;

    private Spinner baseSpinner;
    private Spinner targetSpinner;

    private SeekBar seekBar60;
    private SeekBar seekBar230;
    private SeekBar seekBar910;
    private SeekBar seekBar3600;
    private SeekBar seekBar14000;

    SetterPresenter presenter = new SetterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setter);

        detailSettingLayout = (ConstraintLayout) findViewById(R.id.detailSettingLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        baseSpinner = (Spinner) findViewById(R.id.spinner1);
        targetSpinner = (Spinner) findViewById(R.id.spinner2);

        seekBar60 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar230 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar910 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar3600 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar14000 = (SeekBar) findViewById(R.id.seekBar5);

        setSupportActionBar(toolbar);

        baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = detailSettingLayout.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = detailSettingLayout.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        targetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int childCount = detailSettingLayout.getChildCount();
                for (int i = 0; i < childCount; ++i) {
                    View v = detailSettingLayout.getChildAt(i);
                    v.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        presenter.onCreate();

        seekBar60.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarProgressChanged(seekBar);
            }
        });

        seekBar230.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarProgressChanged(seekBar);
            }
        });

        seekBar910.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarProgressChanged(seekBar);
            }
        });

        seekBar3600.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarProgressChanged(seekBar);
            }
        });

        seekBar14000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarProgressChanged(seekBar);
            }
        });
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

    public void onSeekBarProgressChanged(SeekBar seekBar) {
        String frequency = (String) seekBar.getTag();

        if (frequency.contains("k")) {
            frequency.replace("kHz", "");
        } else {
            frequency.replace("Hz", "");
        }

        presenter.onSeekBarChanged(Integer.parseInt(frequency), seekBar.getProgress());
    }

    private void enabledSeekBars() {
        final int childCount = detailSettingLayout.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View v = detailSettingLayout.getChildAt(i);
            v.setEnabled(true);
        }
    }

    public void onApplyBtnClicked(View v) {
        String baseName = this.baseSpinner.getSelectedItem().toString();
        String targetName = this.targetSpinner.getSelectedItem().toString();
        enabledSeekBars();
        presenter.onApplyBtnSelected(baseName, targetName);
    }

    @Override
    public void setSeekBars(int[] amplitudes) {
        //preset대로 seekbar를 조절합니다
    }
}
