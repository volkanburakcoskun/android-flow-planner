package com.example.burak.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.burak.myapplication.R;

public class alarmNotificationReceiver extends BroadcastReceiver {
    String text="Check Your Tasks!";
    String title="Let it Flow";
    int id=1;

    @Override

    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND)
                .setContentInfo("Info");
        NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id,builder.build());
    }
}
