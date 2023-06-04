package com.example.a_assmr.NotificationService;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.a_assmr.NotificationService.Controller.NotificationServiceController;
import com.example.a_assmr.R;

public class NotificationService {
    Context context;
    NotificationServiceController notificationServiceController;
    public NotificationService(Context context) {
        this.context = context;
        this.notificationServiceController = new NotificationServiceController(context);
    }

    public void pushNotifications() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("push-notification", "the sequence", NotificationManager.IMPORTANCE_DEFAULT);
            System.out.println("ok");
        } else {
            System.out.println("ahay ka");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, String.valueOf(1))
                    .setSmallIcon(R.drawable.baseline_error_24)
                    .setContentTitle("tests")
                    .setContentText("tests-content")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
            notificationServiceController.getNotifications(42, "klent@gmail.com");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(1, builder.build());
        }
    }
}