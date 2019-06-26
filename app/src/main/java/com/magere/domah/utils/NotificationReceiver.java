package com.magere.domah.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.magere.domah.MainActivity;
import com.magere.domah.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent onOpenActivity = new Intent(context, MainActivity.class);

        onOpenActivity.setFlags(FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 1, onOpenActivity, FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("DEBUG");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentText("DEBUG");
        builder.setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }
}
