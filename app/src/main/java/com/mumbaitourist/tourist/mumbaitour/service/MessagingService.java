package com.mumbaitourist.tourist.mumbaitour.service;

import androidx.core.app.NotificationCompat;

public class MessagingService  {
    private static final String LOG_TAG = MessagingService.class.getSimpleName();

   /* @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(LOG_TAG, "Message from server " + remoteMessage.getFrom());

     *//*   if (remoteMessage.getData().size() > 0) {
            sendNotification(null, remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification(), null);
        }
    }*//*

  *//*  private void sendNotification(RemoteMessage.Notification notification,
                                  Map<String, String> data) {
        String title;
        String body;

        if(notification == null){
            title = "New Item Details";
            body = data.get("itemName")+" "+data.get("itemPrice")+" "+data.get("location");
        }else{
            title = notification.getTitle();
            body  = notification.getBody();
        }


        Intent intent = new Intent(this,  WeatherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, "fcm-instance-specific")
                        .setSmallIcon(R.drawable.worli_bridge)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }*/
    //}
}
