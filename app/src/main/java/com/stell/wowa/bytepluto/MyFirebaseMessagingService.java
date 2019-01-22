package com.stell.wowa.bytepluto;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "xx MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Reveived a firebase notifcation: "
                    + "\nBody    : " + remoteMessage.getNotification().getBody()
                    + "\nTitle   : " + remoteMessage.getNotification().getTitle()
            );
        }
    }
}