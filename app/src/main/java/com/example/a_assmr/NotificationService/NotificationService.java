package com.example.a_assmr.NotificationService;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.a_assmr.MainActivity;
import com.example.a_assmr.NotificationService.Controller.NotificationServiceController;
import com.example.a_assmr.NotificationService.Interface.NotificationResponseInterface;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModel;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModelContainer;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.ChatRoom;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import java.util.List;

public class NotificationService implements NotificationResponseInterface {
    Context context;
    NotificationServiceController notificationServiceController;
    NotificationManagerCompat managerCompat;

    public NotificationService(Context context) {
        this.context = context;
        this.notificationServiceController = new NotificationServiceController(context, NotificationService.this);
        this.managerCompat = NotificationManagerCompat.from(context);
    }

    public void pushNotifications(int userID, String userEmail) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("push-notification", "the sequence", NotificationManager.IMPORTANCE_DEFAULT);
            System.out.println("minSDK > 23");
        } else {
            notificationServiceController.getNotifications(userID, userEmail);
        }
    }

    @Override
    public void notificationResponse(NotificationServiceModelContainer notificationServiceModelContainer) {
        if (notificationServiceModelContainer.getCode() == 200) {
            int idx = 0;
            for(NotificationServiceModel nsm: notificationServiceModelContainer.getNotificationServiceModelList()) {
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, String.valueOf(1))
                        .setSmallIcon(R.drawable.baseline_error_24)
                        .setContentTitle(nsm.getUserfname())
                        .setContentText(nsm.getNOTIF_TYP())
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                notificationServiceController.updateActiveUserNotifications(nsm.getID(), nsm.getNOTIF_TYP());

                if(nsm.getNOTIF_TYP().equals("MSSGE")) {
                    Intent intent = new Intent(context, ChatRoom.class);

                    intent.putExtra("message_sender", nsm.getEmail());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

                    builder1.setContentIntent(pendingIntent);
                }

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) !=
                        PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                managerCompat.notify(idx, builder1.build());

                idx+=1;
            }
        }
    }
}