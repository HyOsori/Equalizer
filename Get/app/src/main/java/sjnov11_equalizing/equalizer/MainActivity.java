package sjnov11_equalizing.equalizer;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final String RECORDED_FILE = "/sdcard/recorded.mp4";
    MediaRecorder recorder = null;

    private final int MEASURING = 10;
    private final int NOISE = 2;

    // Frequency Tone Generator
    private final int duration = 100; // seconds (not correct, so give enough time)
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    // private final double freqOfTone = 500; // hz
    private final byte generatedSnd[] = new byte[2 * numSamples];
    private final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
            sampleRate, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT, numSamples,
            AudioTrack.MODE_STATIC);    // particular frequency audio

    private Timer _timer;
    private Runnable _runnable;
    private Handler _handler;
    Handler testhandler = new Handler();

    private final ArrayList<Integer> amplitudes = new ArrayList<Integer>();
    private int sum_amplitude = 0;


    //Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Button btnRecord = (Button)findViewById(R.id.btnRecord);
        Button btnStopRecord = (Button)findViewById(R.id.btnStopRecord);
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnStopPlay = (Button)findViewById(R.id.btnStopPlay);
        */
    }


    protected void onDestroy() {
        // killMediaPlayer();
        killMediaRecorder();
        super.onDestroy();
    }

    protected void onPause() {
        killMediaRecorder();
        // killMediaPlayer();
        super.onPause();
    }

    /*
    private void killMediaPlayer() {
        if(player != null) {
            try {
                player.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    */

    private void killMediaRecorder() {
        if(recorder != null) {
            try {
                recorder.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        player.prepare();;
        player.start();
    }


    private void stopAudio() {
        if(player != null) {
            playbackPosition = player.getCurrentPosition();
            player.pause();
        }
    }
    */




    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btn300Hz:
                record(300);
                break;
            case R.id.btn600Hz:
                record(600);
                break;
            case R.id.btn1KHz:
                record(1000);
                break;
            case R.id.btn2KHz:
                record(2000);
                break;

            /*
            case R.id.btnStopRecord:
                stopRecord();
                break;
            case R.id.btnPlay:
                filePlay();
                break;
            case R.id.btnStopPlay:
                fileStop();
                break;
             */
            //case R.id.btnMax:
                //playFreq();
                // Toast.makeText(getApplicationContext(), "Max : " + recorder.getMaxAmplitude(), Toast.LENGTH_LONG).show();

        }

    }



    // 측정
    public void record(int freq) {
        if(recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(RECORDED_FILE);

        final int freqOfTone = freq;


        try {
            Toast.makeText(getApplicationContext(), "Start Recording", Toast.LENGTH_LONG).show();

            recorder.prepare();
            recorder.start();

            // 녹음 종료
            _runnable = new Runnable() {
                @Override
                public void run() {
                    if(recorder == null) {
                        return;
                    }
                    _timer.cancel();
                    audioTrack.stop();
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                    Toast.makeText(getApplicationContext(),
                            "Stop Recording", Toast.LENGTH_LONG).show();

                    // Average Amplitudes
                    // Except index 0, 1 noising part for accuracy
                    for(int i = NOISE; i < amplitudes.size(); i ++){
                        Log.d("arraylist", "result : " + amplitudes.get(i));
                        sum_amplitude += amplitudes.get(i);
                    }
                    switch (freqOfTone){
                        case 300:
                            ((TextView) findViewById(R.id.tv300HzAverage)).setText(String.valueOf(sum_amplitude / (MEASURING - NOISE - 1)));
                            sum_amplitude = 0;
                            amplitudes.clear();
                            break;
                        case 600:
                            ((TextView) findViewById(R.id.tv600HzAverage)).setText(String.valueOf(sum_amplitude / (MEASURING - NOISE - 1)));
                            sum_amplitude = 0;
                            amplitudes.clear();
                            break;
                        case 1000:
                            ((TextView) findViewById(R.id.tv1kHzAverage)).setText(String.valueOf(sum_amplitude / (MEASURING - NOISE - 1)));
                            sum_amplitude = 0;
                            amplitudes.clear();
                            break;
                        case 2000:
                            ((TextView) findViewById(R.id.tv2kHzAverage)).setText(String.valueOf(sum_amplitude / (MEASURING - NOISE - 1)));
                            sum_amplitude = 0;
                            amplitudes.clear();
                            break;
                    }
                }

            };

            // 1초마다 Max Amplitude 측정
            //recorder.getMaxAmplitude(); // Initialize

            _timer = new Timer();
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(recorder == null)
                                return;
                            int current_amplitude = recorder.getMaxAmplitude();
                            switch (freqOfTone){
                                case 300:
                                    ((TextView) findViewById(R.id.tv300HzAmplitude)).setText(String.valueOf(current_amplitude));
                                    amplitudes.add(current_amplitude);
                                    break;
                                case 600:
                                    ((TextView) findViewById(R.id.tv600HzAmplitude)).setText(String.valueOf(current_amplitude));
                                    amplitudes.add(current_amplitude);
                                    break;
                                case 1000:
                                    ((TextView) findViewById(R.id.tv1kHzAmplitude)).setText(String.valueOf(current_amplitude));
                                    amplitudes.add(current_amplitude);
                                    break;
                                case 2000:
                                    ((TextView) findViewById(R.id.tv2kHzAmplitude)).setText(String.valueOf(current_amplitude));
                                    amplitudes.add(current_amplitude);
                                    break;
                            }

                        }
                    });
                }
            },1000,1000);


            _handler = new Handler();
            _handler.postDelayed(_runnable, MEASURING * 1000);

            switch (freqOfTone){
                case 300:
                    playFreq(300);
                    break;
                case 600:
                    playFreq(600);
                    break;
                case 1000:
                    playFreq(1000);
                    break;
                case 2000:
                    playFreq(2000);
                    break;
            }


        } catch (Exception e) {
            Log.e("SampleAudioRecorder", "Exception : ", e);
        }
    }

    /*
    public void stopRecord() {
        if(recorder == null) {
            return;
        }
        recorder.stop();
        //Toast.makeText(getApplicationContext(), "Max : " + recorder.getMaxAmplitude(), Toast.LENGTH_LONG).show();
        recorder.release();

        recorder = null;
        _timer.cancel();

        Toast.makeText(getApplicationContext(),
                "Stop Recording", Toast.LENGTH_LONG).show();
    }


    public void filePlay() {
        try {
            playAudio(RECORDED_FILE);
            Toast.makeText(getApplicationContext(), "Play now", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fileStop() {
        try {
            stopAudio();
            Toast.makeText(getApplicationContext(), "Stop playing", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    void genTone(int freq){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freq));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    void playFreq(int freq){
        genTone(freq);

        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();

    }


}
