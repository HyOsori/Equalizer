package com.example.rlawk.tutorial_eq;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.audiofx.EnvironmentalReverb;
import android.media.audiofx.Equalizer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    Equalizer mEqualizer;
    BackPressCloseHandler backPressCloseHandler;
    int progress1, progress2, progress3, progress4, progress5;
    boolean clickbar = false;
    SeekBar mBand_1;
    SeekBar mBand_2;
    SeekBar mBand_3;
    SeekBar mBand_4;
    SeekBar mBand_5;

    TextView mVal_1;
    TextView mVal_2;
    TextView mVal_3;
    TextView mVal_4;
    TextView mVal_5;

    TextView mInfo_1;
    TextView mInfo_2;
    TextView mInfo_3;
    TextView mInfo_4;
    TextView mInfo_5;

    //Button start;
    //Button end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try{
            mEqualizer = null;
            mEqualizer = new Equalizer(0, 0);

            mEqualizer.setEnabled(true);
        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this,MyService.class);
        //startActivity(intent);
        //startService(intent);
        initialize();

        mBand_1 = (SeekBar) findViewById(R.id.band_1);
        mBand_2 = (SeekBar) findViewById(R.id.band_2);
        mBand_3 = (SeekBar) findViewById(R.id.band_3);
        mBand_4 = (SeekBar) findViewById(R.id.band_4);
        mBand_5 = (SeekBar) findViewById(R.id.band_5);

        mVal_1 = (TextView) findViewById(R.id.val_1);
        mVal_2 = (TextView) findViewById(R.id.val_2);
        mVal_3 = (TextView) findViewById(R.id.val_3);
        mVal_4 = (TextView) findViewById(R.id.val_4);
        mVal_5 = (TextView) findViewById(R.id.val_5);

        mInfo_1 = (TextView) findViewById(R.id.info_1);
        mInfo_2 = (TextView) findViewById(R.id.info_2);
        mInfo_3 = (TextView) findViewById(R.id.info_3);
        mInfo_4 = (TextView) findViewById(R.id.info_4);
        mInfo_5 = (TextView) findViewById(R.id.info_5);

        // 주어진 대역의 gain set을 가져와서 seekBar 상태 변경
        mBand_1.setProgress(mEqualizer.getBandLevel((short) 0));
        mBand_2.setProgress(mEqualizer.getBandLevel((short) 1));
        mBand_3.setProgress(mEqualizer.getBandLevel((short) 2));
        mBand_4.setProgress(mEqualizer.getBandLevel((short) 3));
        mBand_5.setProgress(mEqualizer.getBandLevel((short) 4));

        mVal_1.setText(String.valueOf(mBand_1.getProgress()));
        mVal_2.setText(String.valueOf(mBand_2.getProgress()));
        mVal_3.setText(String.valueOf(mBand_3.getProgress()));
        mVal_4.setText(String.valueOf(mBand_4.getProgress()));
        mVal_5.setText(String.valueOf(mBand_5.getProgress()));

        mInfo_1.setText(String.valueOf(mEqualizer.getBandFreqRange((short) 0)[0]) + " ~ " + String.valueOf(mEqualizer.getBandFreqRange((short) 0)[1]));
        mInfo_2.setText(String.valueOf(mEqualizer.getBandFreqRange((short) 1)[0]) + " ~ " + String.valueOf(mEqualizer.getBandFreqRange((short) 1)[1]));
        mInfo_3.setText(String.valueOf(mEqualizer.getBandFreqRange((short) 2)[0]) + " ~ " + String.valueOf(mEqualizer.getBandFreqRange((short) 2)[1]));
        mInfo_4.setText(String.valueOf(mEqualizer.getBandFreqRange((short) 3)[0]) + " ~ " + String.valueOf(mEqualizer.getBandFreqRange((short) 3)[1]));
        mInfo_5.setText(String.valueOf(mEqualizer.getBandFreqRange((short) 4)[0]) + " ~ " + String.valueOf(mEqualizer.getBandFreqRange((short) 4)[1]));
        //setButtonStart();
        //setButtonEnd();

        mBand_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress1 = progress;
                mEqualizer.setBandLevel((short) 0, (short) (progress1 - 1500)); //progress는 0부터 3000이므로 1500을 빼준다.
                mVal_1.setText(String.valueOf(mEqualizer.getBandLevel((short) 0)));
                Log.d("CHANGE BAND", progress1+"");
                //stopService(intent)
                mBand_1.setProgress(progress1);
            }

            // 최초에 탭하여 드래그 시작할때 발생
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){


            }

            // 드래그를 멈출 때 발생
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.d("on", "StopTrackingTouch");

            }
        });

        mBand_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress2=progress;
                mEqualizer.setBandLevel((short) 1, (short) (progress - 1500)); //progress는 0부터 3000이므로 1500을 빼준다.
                mVal_2.setText(String.valueOf(mEqualizer.getBandLevel((short) 1)));
                mBand_2.setProgress(progress2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("on", "StopTrackingTouch");
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                stopService(intent);
//                intent.putExtra("value1", progress1 - 1500);
//                intent.putExtra("value2", progress2 - 1500);
//                intent.putExtra("value3", progress3 - 1500);
//                intent.putExtra("value4", progress4 - 1500);
//                intent.putExtra("value5", progress5 - 1500);
//                startService(intent);
            }
        });

        mBand_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress3=progress;
                mEqualizer.setBandLevel((short) 2, (short) (progress - 1500)); //progress는 0부터 3000이므로 1500을 빼준다.
                mVal_3.setText(String.valueOf(mEqualizer.getBandLevel((short) 2)));
                mBand_3.setProgress(progress3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("on", "StopTrackingTouch");
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                stopService(intent);
//                intent.putExtra("value1", progress1 - 1500);
//                intent.putExtra("value2", progress2 - 1500);
//                intent.putExtra("value3", progress3 - 1500);
//                intent.putExtra("value4", progress4 - 1500);
//                intent.putExtra("value5", progress5 - 1500);
//                startService(intent);

            }
        });

        mBand_4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress4=progress;
                mEqualizer.setBandLevel((short) 3, (short) (progress - 1500)); //progress는 0부터 3000이므로 1500을 빼준다.
                mVal_4.setText(String.valueOf(mEqualizer.getBandLevel((short) 3)));
                mBand_4.setProgress(progress4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("on", "StopTrackingTouch");
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                stopService(intent);
//                intent.putExtra("value1", progress1 - 1500);
//                intent.putExtra("value2", progress2 - 1500);
//                intent.putExtra("value3", progress3 - 1500);
//                intent.putExtra("value4", progress4 - 1500);
//                intent.putExtra("value5", progress5 - 1500);
//                startService(intent);

            }
        });

        mBand_5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress5=progress;
                mEqualizer.setBandLevel((short) 4, (short) (progress - 1500)); //progress는 0부터 3000이므로 1500을 빼준다.
                mVal_5.setText(String.valueOf(mEqualizer.getBandLevel((short) 4)));
                mBand_5.setProgress(progress5);
        }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d Service(intent);

            }
        });
        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }
    /*
    void setButtonStart(){
        start=(Button)findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 시작",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });
    }

    void setButtonEnd(){
        end = (Button)findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 끝",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
    }
    */
    void initialize(){
        progress1 = 1500;
        progress2 = 1500;
        progress3 = 1500;
        progress4 = 1500;
        progress5 = 1500;
    }
    @Override
    protected void onDestroy() {

        Intent intent = new Intent(MainActivity.this,MyService.class);
        //startService(intent);
        intent.putExtra("value1", progress1 - 1500);
        intent.putExtra("value2", progress2 - 1500);
        intent.putExtra("value3", progress3 - 1500);
        intent.putExtra("value4", progress4 - 1500);
        intent.putExtra("value5", progress5 - 1500);
        startService(intent);
        super.onDestroy();
    }

    public void refresh(View view){
        onRestart();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*
    public void ReadData(){

        File sdcard = Environment.getExternalStorageDirectory();

        // Get the text file
        File file = new File(sdcard, "device_data.json");

        // Read text from file
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        //JSONObject json = new JSONObject(text);

    }
    */

}
