package com.appdavid.proyectocsi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appdavid.proyectocsi.utilities_gcm.QuickstartPreferences;
import com.appdavid.proyectocsi.utilities_gcm.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button btn;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;



    public static final String USER_NAME = "USERNAME";

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register_GCM();

        editTextUserName = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);
        btn              = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "Entraste a este boton");
                username = editTextUserName.getText().toString();
                password = editTextPassword.getText().toString();

                login(username, password);

            }
        });


    }


    private void login(String username, String password) {
        Log.e("login", "Entraste");

        class login_task extends AsyncTask{

            String userAgent = "Mozilla/5.0 (X11; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0";
            String response;


            @Override
            protected Object doInBackground(Object[] params) {
                String dir = "http://192.168.57.1/cosas/config.php";
                String nombre = (String) params[0];
                String contra = (String)params[1];

                try {
                    URL myUrl = new URL(dir);
                    HttpURLConnection urlConnection = (HttpURLConnection)myUrl.openConnection();
                    urlConnection.addRequestProperty("User-Agent",userAgent);
                    String nombreEnviar = URLEncoder.encode(nombre,"UTF-8");
                    String contraEnviar = URLEncoder.encode(contra,"UTF-8");
                    //para establecer conexion de escritura
                    urlConnection.setDoOutput(true);
                    //indicamos el tipo de request
                    urlConnection.setRequestMethod("POST");

                    // Indicamos un timeout de 10 segundos
                    urlConnection.setReadTimeout(5 * 1000);

                    HashMap<String,String> datos = new HashMap<>();
                    datos.put("nombre", (String) params[0]);
                    datos.put("contraseña", (String) params[1]);
                    Log.e("INFO datos", String.valueOf(datos));
                    StringBuilder cadena = new StringBuilder();
                    boolean first = true;
                    for (Map.Entry<String,String>entry:datos.entrySet()){
                        if(first) {
                            first = false;
                        }else {
                            cadena.append(" & ");
                        }
                        cadena.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                        cadena.append("=");
                        cadena.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
                    }

                    try {
                        OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                        Log.e("POST",cadena.toString());
                       out.write(cadena.toString());
                        out.close();
                    }catch (Exception e ){
                        Log.e("Error", String.valueOf(e));
                        cancel(true);

                    }


                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));

                    while((response = in.readLine()) != null) {

                        if (!response.equals("logueado")){
                            Log.e("Error de sesion",response);
                            cancel(true);
                        }
                    }

                    in.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                if (!isCancelled()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Logueado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.appdavid.proyectocsi.MainEmpleado");
                            startActivity(intent);
                        }
                    });

                }
            }
            protected void onCancelled() {
                super.onCancelled();

                       Toast.makeText(getBaseContext(),"Se rechazó conexion",Toast.LENGTH_SHORT).show();

            }
        }


        login_task loginTask = new login_task();
        loginTask.execute(username, password);
    }



    private void register_GCM(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(RegistrationIntentService.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.e("Succes", String.valueOf(R.string.gcm_send_message));
                } else {
                    Log.e("No Succes", String.valueOf(R.string.token_error_message));
                }
            }
        };



        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
