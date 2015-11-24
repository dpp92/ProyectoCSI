package com.appdavid.proyectocsi.utilities_gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.appdavid.proyectocsi.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by dpp on 19/11/15.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    public static final String SENT_TOKEN_TO_SERVER="sentTokenToServer";
    public static final String GCM_TOKEN = "gcmToken";
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Hace un llamado al intent para pedir un token

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        try{

            InstanceID instanceID = InstanceID.getInstance(this);
            String senderID=getResources().getString(R.string.gcm_defaultSenderID);


            String token = instanceID.getToken(senderID, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);
            sendRegistrationToServer(token);

            sharedPreferences.edit().putString(GCM_TOKEN, token).apply();


        } catch (IOException e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            e.printStackTrace();
            sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, false).apply();
        }
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


    private void sendRegistrationToServer(String token){


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, true).apply();

    }


}
