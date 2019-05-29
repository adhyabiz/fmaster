package com.amansingh.foxfire.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.amansingh.foxfire.Activity.HomeActivity;
import com.amansingh.foxfire.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "onMessageReceived: msg " + remoteMessage);
        Log.e(TAG, "onMessageReceived: msg from " + remoteMessage.getFrom());
        Log.e(TAG, "onMessageReceived: msg data " + remoteMessage.getData());
        Map<String, String> dataMap = remoteMessage.getData();
        String title = dataMap.get("title");
        String msg = dataMap.get("body");
        Log.e(TAG, "onMessageReceived: title and msg " + title + " " + msg);
        showNotification(msg, title);
    }

    private void showNotification(String body, String title) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(defaultSoundUri);

        Intent resultIntent = new Intent(this, HomeActivity.class);
        TaskStackBuilder stackBuilder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(resultPendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
