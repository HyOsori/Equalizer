package com.example.dooms.eq;

import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar mSeekbar;
    private TextView mTextView;
    private int mSeekBarVal = 0;
    private MediaPlayer mPlayer = new MediaPlayer();
    int sessionId = mPlayer.getAudioSessionId();
    private Equalizer mEqualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekbar = (SeekBar)findViewById(R.id.seekBar1);
        mTextView = (TextView)findViewById(R.id.valOfSeekBar);

        try {
            mEqualizer = null;
            mEqualizer = new Equalizer(0, sessionId );
            mEqualizer.setEnabled(true);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        short bands = mEqualizer.getNumberOfBands();
        if (bands >= 5) {
            mEqualizer.setBandLevel((short) 0, (short) 300);
            mEqualizer.setBandLevel((short) 1, (short) 500);
            mEqualizer.setBandLevel((short) 2, (short) 0);
            mEqualizer.setBandLevel((short) 3, (short) 0);
            mEqualizer.setBandLevel((short) 4, (short) 0);
        }
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeekBarVal = progress;
                mEqualizer.setBandLevel((short) 0, (short) mSeekBarVal);
                mTextView.setText(String.valueOf(mSeekBarVal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar.incrementProgressBy(1);
    }
}
