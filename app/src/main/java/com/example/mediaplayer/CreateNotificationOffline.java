package com.example.mediaplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CreateNotificationOffline {
    public static final String CHANNELID = "channel2";

    public static final String PLAY = "play";
    public static final String PAUSE = "pause";
    public static Notification notification;


    public static void createNotificationOffline(Context context, String title, int playbutton) {
        Log.d("debug", "Notification!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.t1);

        Intent intentPlay = new Intent(context, NotificationActionService.class)
                .setAction(PLAY);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0,
                intentPlay, PendingIntent.FLAG_MUTABLE);

        notification = new NotificationCompat.Builder(context, CHANNELID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_music_note)
                .setContentTitle(title)
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true)//show notification for only first time
                .setShowWhen(false)
                .addAction(playbutton, "Play", pendingIntentPlay)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setAutoCancel(false)
                .build();

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("debug", "hereprob");
            return;
        }
        Log.d("debug", "hereprobNo");
        notificationManagerCompat.notify(1, notification);
    }

}
