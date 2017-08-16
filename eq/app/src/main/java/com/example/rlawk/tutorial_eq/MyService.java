package com.example.rlawk.tutorial_eq;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    NotificationManager notificationManager;
    ServiceThread serviceThread;
    Notification notification;
    MyServiceHandler handler;
    int[] arr;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 백그라운드에서 실행되는 동작들
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        handler = new MyServiceHandler();

        arr=new int[5];
        for(int i = 0;i<5;i++){
            arr[i]=intent.getExtras().getInt("value"+(i+1));
        }

        serviceThread = new ServiceThread(handler, arr);

        serviceThread.start();
        //handler.postDelayed(serviceThread, 5000);
        return START_STICKY;
    }

    // 서비스가 종료될 때 작업
    public void onDestroy() {
        //serviceThread.stopForever();
        handler.removeCallbacks(serviceThread);
        super.onDestroy();
        serviceThread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class MyServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Log.d("handleMessage", "enter");
            Intent intent = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setSmallIcon(R.drawable.logo)
                    .setTicker("알림!!!")
                    .setContentIntent(pendingIntent).build();

            //소리추가
            //notification.defaults = Notification.DEFAULT_SOUND;

            //알림 소리를 한번만 내도록
            //notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            notification.flags = Notification.FLAG_AUTO_CANCEL;


            notificationManager.notify( 777 , notification);

            //토스트 띄우기
            Toast.makeText(MyService.this, "뜸?", Toast.LENGTH_LONG).show();
        }
    };
}

