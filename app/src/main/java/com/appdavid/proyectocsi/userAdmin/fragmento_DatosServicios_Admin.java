package com.appdavid.proyectocsi.userAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appdavid.proyectocsi.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dpp on 11/12/15.
 */
public class fragmento_DatosServicios_Admin extends Fragment {

    Context context;
    HttpURLConnection httpURLConnection;
    ProgressDialog progressDialog;
    AdapDatosServiciosAdmin adapDatosServiciosAdmin;
    ListView listServiciosAdmin;
    ArrayList<DatosServiciosAdmin> datosServiciosAdminsList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.admin_fragmentos_datoservicios, container, false);
        context = container.getContext();
        datosServiciosAdminsList = new ArrayList<DatosServiciosAdmin>();
        listServiciosAdmin = (ListView) rootView.findViewById(R.id.admin_lista_servicio);
        adapDatosServiciosAdmin = new AdapDatosServiciosAdmin(context, R.layout.admin_lista_servicios, R.id.name_adminServicios, datosServiciosAdminsList);
        listServiciosAdmin.setAdapter(adapDatosServiciosAdmin);

        return rootView;
    }

    public void onStart() {

        super.onStart();

        //execute background task

        BackTask bt = new BackTask();

        bt.execute();
    }

    private class BackTask extends AsyncTask<Void,Void,Void> {

        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(context);

            progressDialog.setTitle("Retrieving data");

            progressDialog.setMessage("Please wait.");

            progressDialog.setCancelable(true);

            progressDialog.setIndeterminate(true);

            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            InputStream inputStream= null;
            String result="";
            try {

                URL url = new URL("http://172.16.60.1/cosas/verregistros.php");
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                if(progressDialog!=null){

                    progressDialog.dismiss(); //close the dialog if error occurs

                    Log.e("ERROR", e.getMessage());

                }
            }

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        httpURLConnection.getInputStream()));
                String inputLine;
                StringBuilder sb = new StringBuilder();
                while((inputLine = in.readLine()) != null){
                    sb.append(inputLine+"\n");
                }

                in.close();
                result = sb.toString();
            } catch (IOException e) {
                Log.e("ERROR", "Error converting result " + e.toString());
            }

            //parsear json

            try {
                result=result.substring(result.indexOf("["));
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i<jsonArray.length();i++){
                    JSONObject json_data =jsonArray.getJSONObject(i);
                DatosServiciosAdmin serviciosAdmin= new DatosServiciosAdmin();
                serviciosAdmin.setNombre(json_data.getString("name"));
                serviciosAdmin.setDescripcion(json_data.getString("description"));
                datosServiciosAdminsList.add(serviciosAdmin);

                }

            }catch (Exception e){
                Log.e("ERROR", "Error pasting data "+e.toString());
            }

            return null;
        }

        protected void onPostExecute(Void result){



            if(progressDialog!=null) progressDialog.dismiss(); //close dialog

            Log.e("size", datosServiciosAdminsList.size() + "");

            adapDatosServiciosAdmin.notifyDataSetChanged(); //notify the ListView to get new records



        }
    }
}
