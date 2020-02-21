package com.example.samplestickerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MiFirebaseMessagingService extends FirebaseMessagingService {
    public  static  final  String  TAG  =  " NOTICIAS " ;

    public  void  onMessageReceived ( RemoteMessage remoteMessage ) {
        super . onMessageReceived (remoteMessage);

        String from = remoteMessage .getFrom();
        Log. d ( TAG , " Mensaje Recibido de: "  + from);

        if (remoteMessage.getNotification() !=  null) {
            Log . d ( TAG , " notificación: "  + remoteMessage . getNotification () . getBody ());

            mostrarNotificacion (remoteMessage . getNotification () . getTitle (), remoteMessage . getNotification () . getBody ());
        }

        if (remoteMessage . getData () . size () >  0 ) {
            Log . d ( TAG , " Datos: "  + remoteMessage . getData ());
        }

    }

    private void mostrarNotificacion ( String  title , String  body ) {

        Intent intent =  new  Intent ( this , EntryActivity.class);
        intent . setFlags ( Intent . FLAG_ACTIVITY_CLEAR_TOP );
        PendingIntent pendingIntent =  PendingIntent . getActivity ( this , 0 , intent, PendingIntent . FLAG_ONE_SHOT );

        Uri soundUri =  RingtoneManager. getDefaultUri ( RingtoneManager . TYPE_NOTIFICATION );

        NotificationCompat. Builder notificationBuilder =  new  NotificationCompat.Builder(this)
                .setSmallIcon (R.drawable.noti)
                .setContentTitle (title)
                .setContentText (body)
                .setAutoCancel ( true )
                .setSound (soundUri)
                .setContentIntent (pendingIntent);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.navid));

        NotificationManager notificationManager = ( NotificationManager ) getSystemService ( Context. NOTIFICATION_SERVICE );
        notificationManager . notify ( 0 , notificationBuilder . build ());

    }

}