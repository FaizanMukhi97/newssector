package com.example.mahima.slidedemo.utility.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.mahima.slidedemo.Navbar;
import com.example.mahima.slidedemo.Useractivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseInstanceService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            super.onMessageReceived(remoteMessage);

            if (remoteMessage.getData().isEmpty())
                showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            else
                showNotification(remoteMessage.getData());
        } catch (Exception e) {
            Log.e("onMessageReceived", e.toString());
        }
    }

    private void showNotification(Map<String, String> data) {
        try {
            String title = data.get("title").toString();
            String body = data.get("body").toString();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL_ID = "com.example.mahima.slidedemo";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Notification Channel Description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    //.setSmallIcon( R.mipmap.ic_launcher )
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentInfo("Content Info");

            notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
        } catch (Exception e) {
            Log.e("showNotification", e.toString());
        }
    }

    private void showNotification(String title, String body) {

        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL_ID = "com.example.mahima.slidedemo";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Notification Channel Description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

            PendingIntent pi = PendingIntent.getActivity(this, 1,
                    new Intent(this, Navbar.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    //.setSmallIcon( R.mipmap.ic_launcher )
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentIntent(pi)
                    .setContentInfo("Content Info");

            notificationManager.notify(new Random().nextInt(), notificationBuilder.build());


        } catch (Exception e) {
            Log.e("showNotification", e.toString());
        }
    }

    @Override
    public void onNewToken(String newToken) {
        try {
            super.onNewToken(newToken);

//            Shared sp = new Shared( this );
//
//            String existingToken = sp.read( "UserDeviceTokanID", "null" );
//
//            if (!newToken.equals( existingToken )) {
//                sp.write( "UserDeviceTokanID", newToken );
//            }

            Log.d("newToken Firebase", newToken);
        } catch (Exception e) {
            Log.e("onNewToken", e.toString());
        }
    }
}
