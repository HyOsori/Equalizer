package com.equalizer.hyosori.equalizer.view;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.Equalizer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.equalizer.hyosori.equalizer.R;
import com.equalizer.hyosori.equalizer.presenter.SetterPresenter;

import java.util.ArrayList;


public class SetterActivity extends AppCompatActivity implements SetterView {

    private Toolbar toolbar;
    private ConstraintLayout detailSettingLayout;

    private Spinner baseSpinner;
    private Spinner targetSpinner;

    private SeekBar seekBar60;
    private SeekBar seekBar230;
    private SeekBar seekBar910;
    private SeekBar seekBar3600;

    SetterPresenter presenter = new SetterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setter);

        Toast.makeText(getApplicationContext(), "onCreate!", Toast.LENGTH_SHORT).show();

        detailSettingLayout = (ConstraintLayout) findViewById(R.id.detailSettingLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        baseSpinner = (Spinner) findViewById(R.id.spinner1);
        targetSpinner = (Spinner) findViewById(R.id.spinner2);

        seekBar60 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar230 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar910 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar3600 = (SeekBar) findViewById(R.id.seekBar4);

        setSupportActionBar(toolbar);

//        arraylist = new ArrayAdapter<String>();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, baseSpinner, arraylist);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                this.finish();
                startActivity(getIntent());
            } else {
                Toast.makeText(getApplicationContext(), "CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
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
            startActivityForResult(new Intent(SetterActivity.this, GetterActivity.class), 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSeekBarProgressChanged(SeekBar seekBar) {
        String frequency = (String) seekBar.getTag();

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
        int baseNum = this.baseSpinner.getSelectedItemPosition();
        int targetNum = this.targetSpinner.getSelectedItemPosition();
        enabledSeekBars();
        presenter.onApplyBtnSelected(baseNum, targetNum);
    }
    @Override
    public void setSpinnerData(ArrayList<String> eil_) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eil_);
        this.baseSpinner.setAdapter(adapter);
        this.targetSpinner.setAdapter(adapter);
    }

    @Override
    public void setSeekBars(int[] amplitudes) {
        //preset대로 seekbar를 조절합니다
        seekBar60.setProgress(amplitudes[0] + 1500);
        seekBar230.setProgress(amplitudes[1] + 1500);
        seekBar910.setProgress(amplitudes[2] + 1500);
        seekBar3600.setProgress(amplitudes[3] + 1500);
    }
}
