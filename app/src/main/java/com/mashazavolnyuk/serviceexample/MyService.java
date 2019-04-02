package com.mashazavolnyuk.serviceexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId = "alarm_service";
            NotificationChannel channel = new  NotificationChannel(channelId, "Alarm service", NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            notification = new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(BuildConfig.VERSION_NAME)
                    .setContentText(getApplicationContext().getString(R.string.app_name))
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
            startForeground(22345, notification);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
