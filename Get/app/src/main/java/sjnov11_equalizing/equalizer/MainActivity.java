package sjnov11_equalizing.equalizer;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.AudioFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final String RECORDED_FILE = "/sdcard/recorded.mp4";

    MediaPlayer player;
    MediaRecorder recorder = null;
    int playbackPosition = 0;

    private final int duration = 10; // seconds
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 14000000; // hz

    private Timer _timer;

    private final byte generatedSnd[] = new byte[2 * numSamples];

    //Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecord = (Button)findViewById(R.id.btnRecord);
        Button btnStopRecord = (Button)findViewById(R.id.btnStopRecord);
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnStopPlay = (Button)findViewById(R.id.btnStopPlay);
    }


    protected void onDestroy() {
        killMediaPlayer();
        killMediaRecorder();
        super.onDestroy();
    }

    protected void onPause() {
        killMediaRecorder();
        killMediaPlayer();
        super.onPause();
    }

    private void killMediaPlayer() {
        if(player != null) {
            try {
                player.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void killMediaRecorder() {
        if(recorder != null) {
            try {
                recorder.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

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



    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btnRecord:
                record();
                break;
            case R.id.btnStopRecord:
                stopRecord();
                break;
            case R.id.btnPlay:
                filePlay();
                break;
            case R.id.btnStopPlay:
                fileStop();
                break;
            case R.id.btnMax:
                Toast.makeText(getApplicationContext(), "Max : " + recorder.getMaxAmplitude(), Toast.LENGTH_LONG).show();

        }

    }

    public void record() {
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


        try {
            Toast.makeText(getApplicationContext(), "Start Recording", Toast.LENGTH_LONG).show();

            recorder.prepare();
            recorder.start();

            _timer = new Timer();
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((TextView) findViewById(R.id.textViewDecibelNumber)).setText(String.valueOf(recorder.getMaxAmplitude()));
                        }
                    });
                }
            },1000,1000);

            playFreq();

        } catch (Exception e) {
            Log.e("SampleAudioRecorder", "Exception : ", e);
        }
    }

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

    void genTone(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
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

    void playFreq() {
        genTone();
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();

    }


}
