package com.example.rlawk.tutorial_eq;

import android.content.Intent;
import android.media.audiofx.Equalizer;
import android.os.Handler;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rlawk on 2017-07-05.
 */

public class ServiceThread extends Thread {
    //Equalizer mEqualizer;
    Handler handler;
    int[] arrValue = new int[5];
    boolean isRun = true;

    public ServiceThread(Handler handler, int[] arr) {
        /*
        try{
            mEqualizer = null;
            mEqualizer = new Equalizer(0, 0);
            mEqualizer.setEnabled(true);
        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
        */
        this.handler=handler;
        for(int i = 0;i<5;i++) {
            Log.d("next", "intent");
            Log.d("intent is ", arrValue[i] + "");
        }
        for(int i = 0 ;i< 5;i++){
            arrValue[i]=arr[i];
        }
    }

    public void stopForever(){
        synchronized (this){
            this.isRun = false;

        }
    }

    public void run(){
        TimerTask adTast = new TimerTask() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Timer timer = new Timer();
        timer.schedule(adTast, 0, 1000); // 0초후 첫실행, 3초마다 계속실행
        //for(int i = 0;i<5;i++){
        //    mEqualizer.setBandLevel((short) i, (short) arrValue[i]);
        //}
    }
}
