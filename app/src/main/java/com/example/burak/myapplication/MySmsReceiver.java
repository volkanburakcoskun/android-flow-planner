package com.example.burak.myapplication;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String TAG =
            MySmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";
    public DatabaseHelper db;
    private final String CHANNEL_ID="personal_notifications";
    private final int NOTIFICATION_ID=001;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();
        db=new DatabaseHelper(context);
        SmsMessage[] msgs; String separated[]=new String[4];
        String strMessage = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {

                if (isVersionM) {

                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {

                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                // Log and display the SMS message.
                Log.d(TAG, "onReceive: " + strMessage);
                db.toast(msgs[i].getOriginatingAddress());
                displayNotification(context,"You received an Sms!","Let it Flow");

            }
        }}
    public void displayNotification(Context context,String text,String title) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

    }
}