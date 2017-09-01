package com.example.rlawk.tutorial_eq;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by samsung on 2017-07-28.
 */

public class BackPressCloseHandler {
    private  long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            toast.cancel();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.moveTaskToBack(true);
            activity.finish();
            android.os.Process.killProcess((android.os.Process.myPid()));
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity, "한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT);
        toast.show();
    }
}
