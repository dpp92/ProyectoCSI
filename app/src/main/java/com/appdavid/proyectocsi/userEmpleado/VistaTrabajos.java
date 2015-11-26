package com.appdavid.proyectocsi.userEmpleado;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appdavid.proyectocsi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dpp on 24/11/15.
 */
public class VistaTrabajos extends AppCompatActivity {

    ArrayList<datosHijos> hijos;
    ArrayList<ExpandableListParent> customList = new ArrayList<>();
    public BaseExpandableListAdapter adapter;
    CheckBox finish;
    TextView titulo;
    EditText info;
    int long_info, long_info_update;
    Bundle bundle;
    int terminado;
    HttpURLConnection urlConnection;
    String url ="http://192.168.57.1/cosas/updateTask.php";
    ProgressBar pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_activity_vista_tareas);

        pDialog = (ProgressBar) findViewById(R.id.progressBar);
        finish = (CheckBox)findViewById(R.id.checkBox);
        finish.setEnabled(false);
        hijos = new ArrayList<>();
        titulo = (TextView)findViewById(R.id.titulo);
        info=(EditText)findViewById(R.id.info_tareas);
        //recibimos los datos
        bundle=getIntent().getExtras();
        Toast.makeText(this,bundle.getString("tipo"),Toast.LENGTH_LONG).show();
        titulo.setText(bundle.getString("titulo"));
        info.setText(bundle.getString("contenido"));

        info.setEnabled(false);


        long_info=info.length();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_empleado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit_task:
                info.setEnabled(true);
                finish.setEnabled(true);
                break;
            case R.id.save_tasks:
                long_info_update = info.length();
                if (long_info!=long_info_update){
                    actualizarTrabajo(bundle);
                    pDialog.setVisibility(View.GONE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actualizarTrabajo(Bundle bundle) {

        String msg_cambiado;
        String tipo_task = bundle.getString("tipo");
        String titulo_task = bundle.getString("titulo");
        int id = bundle.getInt("id");
        msg_cambiado= info.getText().toString();

        if (finish.isChecked()){
            terminado = 1;
        }else{
            terminado =0;
        }

        new updateTask().execute(msg_cambiado, tipo_task, titulo_task, String.valueOf(id),String.valueOf(terminado));

    }

    private class updateTask extends AsyncTask<String,Void,String>{



        @Override
        protected String doInBackground(String[] params) {
            String response;
            String name = params[1];
            String title = params[2];
            String contenido = params[0];
            int estado_tarea = Integer.parseInt(params[4]);
            int id= Integer.parseInt(params[3]);
            try{
                URL myUrl = new URL(url);
                urlConnection = (HttpURLConnection)myUrl.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setReadTimeout(5 * 1000);

                HashMap<String,String> datos = new HashMap<>();
                datos.put("content_update", contenido);
                datos.put("tipo", name);
                datos.put("titulo", title);
                datos.put("id", String.valueOf(id));
                datos.put("finish", String.valueOf(estado_tarea));

                hijos.add(new datosHijos(name, title, contenido, id));
                customList.add(new ExpandableListParent(name, hijos));

                StringBuilder cadena = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String,String>entry:datos.entrySet()){
                    if(first) {
                        first = false;
                    }else {
                        cadena.append(" & ");
                    }
                    cadena.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    cadena.append("=");
                    cadena.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
                }
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                Log.e("POST", cadena.toString());
                out.write(cadena.toString());
                out.close();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                urlConnection.getInputStream()));

                while((response = in.readLine()) != null) {

                    if (!response.equals("actualizado")){
                        Log.e("Error al actualizar",response);
                        cancel(true);
                    }
                }

                in.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            adapter = new ExpandListAdapter(getBaseContext(),customList);
            adapter.notifyDataSetChanged();
            Intent intent = new Intent("com.appdavid.proyectocsi.MainEmpleado");
            startActivity(intent);
        }
    }
}
