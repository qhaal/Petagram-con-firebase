package com.developer.albert.mascotas;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Albert on 11/04/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        Log.d(TAG, "Solicitando Token");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);

    }


}
