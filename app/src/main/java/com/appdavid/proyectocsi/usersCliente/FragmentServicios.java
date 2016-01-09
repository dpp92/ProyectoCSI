package com.appdavid.proyectocsi.usersCliente;

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
 * Created by DAVID PUC POOT on 11/11/2015.
 */
public class FragmentServicios extends Fragment {

    Context context;
    private final String SERVICIOS="ERROR SERVICIOS";
    HttpURLConnection httpURLConnection;
    ProgressDialog progressDialog;
    AdaptadorDatosServicios adaptadorDatosServicios;
    ListView listServicios;
    ArrayList<DatosServicios>datosServicioses;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cliente_fragmentos_servicios,container,false);


        context = container.getContext();


        datosServicioses = new ArrayList<DatosServicios>();

        listServicios = (ListView)rootView.findViewById(R.id.servicios_lista);
        adaptadorDatosServicios = new AdaptadorDatosServicios(context,R.layout.cliente_lista_servicios,
                R.id.name,datosServicioses);
        listServicios.setAdapter(adaptadorDatosServicios);

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

                    Log.e(SERVICIOS, e.getMessage());

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
                Log.e(SERVICIOS, "Error converting result " + e.toString());
            }

            //parsear json

            try {
                result=result.substring(result.indexOf("["));
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i<jsonArray.length();i++){
                    JSONObject json_data =jsonArray.getJSONObject(i);
                    DatosServicios datosServicios = new DatosServicios();

                    datosServicios.setName(json_data.getString("name"));
                    datosServicios.setDescription(json_data.getString("description"));
                    datosServicioses.add(datosServicios);

                }

            }catch (Exception e){
                Log.e(SERVICIOS, "Error pasting data "+e.toString());
            }

            return null;
        }

        protected void onPostExecute(Void result){



            if(progressDialog!=null) progressDialog.dismiss(); //close dialog

            Log.e("size", datosServicioses.size() + "");

            adaptadorDatosServicios.notifyDataSetChanged(); //notify the ListView to get new records



        }
    }
}
