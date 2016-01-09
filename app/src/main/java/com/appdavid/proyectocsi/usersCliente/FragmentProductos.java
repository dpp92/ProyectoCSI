package com.appdavid.proyectocsi.usersCliente;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
public class FragmentProductos extends Fragment {
    Context context;

    HttpURLConnection httpURLConnection;
    private final String PRODUCTOS="ERROR PRODUCTOS";
    ProgressDialog progressDialog;
    AdaptadorDatosProductos adaptadorDatosProductos;
    ListView listProductos;
    ArrayList<DatosProductos> datosProductoses;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cliente_fragmento_productos,container,false);
        context = container.getContext();


        datosProductoses = new ArrayList<DatosProductos>();

        listProductos = (ListView)rootView.findViewById(R.id.productos_lista);
        adaptadorDatosProductos = new AdaptadorDatosProductos(context,R.layout.cliente_lista_productos,
                R.id.name,datosProductoses);
        listProductos.setAdapter(adaptadorDatosProductos);

        listProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DatosProductos elegido = (DatosProductos)parent.getItemAtPosition(position);

                //Creamos un paquete para guardar los datos a enviar
                Bundle datosProductoSeleccionado = new Bundle();
                datosProductoSeleccionado.putString("nombreProductoSeleccionado",elegido.getName());
                datosProductoSeleccionado.putString("descripcionProductoSeleccionado",elegido.getDescription());
                //Creamos intent para abrir la nueva clase
                Intent i = new Intent(getContext(), HacerPedido.class);

                CharSequence texto = "Seleccionado: " + elegido.getDescription();
                Toast toast = Toast.makeText(context, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });



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

                    Log.e(PRODUCTOS, e.getMessage());

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
                Log.e(PRODUCTOS, "Error converting result " + e.toString());
            }

            //parsear json

            try {
                result=result.substring(result.indexOf("["));
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i<jsonArray.length();i++){
                    JSONObject json_data =jsonArray.getJSONObject(i);
                    DatosProductos datosProductos = new DatosProductos();

                    //datosProductos.setThumbnailResource(json_data.getString("name"));
                    datosProductos.setName(json_data.getString("name"));
                    datosProductos.setDescription(json_data.getString("description"));
                    datosProductoses.add(datosProductos);

                }

            }catch (Exception e){
                Log.e(PRODUCTOS, "Error pasting data "+e.toString());
            }

            return null;
        }

        protected void onPostExecute(Void result){



            if(progressDialog!=null) progressDialog.dismiss(); //close dialog

            Log.e("size", datosProductoses.size() + "");

            adaptadorDatosProductos.notifyDataSetChanged(); //notify the ListView to get new records



        }
    }
}
