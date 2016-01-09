//package com.appdavid.proyectocsi.userEmpleado;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ExpandableListView;
//
//import com.appdavid.proyectocsi.R;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by DAVID PUC POOT on 12/11/2015.
// */
//public class FragmentHomeEmpleado extends Fragment {
//    Context context;
//    HttpURLConnection urlConnection;
//    public BaseExpandableListAdapter adapter;
//    ExpandableListView lv;
//    private String jsonResult;
//    ProgressDialog progressDialog;
//    ArrayList<datosHijos> hijos;
//    String[] datos;
//    ArrayList<ExpandableListParent> customList = new ArrayList<>();
//    //Algo paso
//    String url = "http://192.168.57.1/cosas/verCosas.php";
//    private String result="";
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.empleado_fragmento_lista_expand_tareas, container, false);
//        Log.e("Sesion", "Entramos a FragmentHome");
//        context= container.getContext();
//
//        lv = (ExpandableListView)rootView.findViewById(R.id.listViewexp);
//        accessWebService();
//        hijos = new ArrayList<>();
//
//        return rootView;
//    }
//
//
//    public void accessWebService() {
//
//        JsonReadTask task = new JsonReadTask();
//        task.execute(new String[]{url});
//    }
//
//
//    public class JsonReadTask extends AsyncTask<String,Void,ArrayList<ExpandableListParent>>{
//        public JsonReadTask() {
//            super();
//        }
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected ArrayList<ExpandableListParent> doInBackground(String... strings) {
//
//            InputStream inputStream= null;
//
//        try{
//            URL myUrl = new URL(strings[0]);
//            urlConnection = (HttpURLConnection)myUrl.openConnection();
//            urlConnection.setDoOutput(true);
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setReadTimeout(5 * 1000);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }try {
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        urlConnection.getInputStream()));
//                String inputLine;
//                StringBuilder sb = new StringBuilder();
//                while((inputLine = in.readLine()) != null){
//                    sb.append(inputLine+"\n");
//                }
//
//                in.close();
//                result = sb.toString();
//
//                Log.e("Resultado",result);
//
//
//            } catch (IOException e) {
//                Log.e("ERROR", "Error converting result " + e.toString());
//            }
//            try{
//                //TODO "Revisar porque hijos aparecen en los dos tipos de grupo
//                JSONArray jsonMainNode = new JSONArray(result);
//                for(int i=0;i<2;i++){
//                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
//                    int id     = jsonChildNode.optInt("id");
//                    String name = jsonChildNode.optString("tipo");
//                    String title = jsonChildNode.optString("titulo");
//                    String contenido = jsonChildNode.optString("contenido");
//
//                    hijos.add(new datosHijos(name,title,contenido,id));
//                    customList.add(new ExpandableListParent(name,hijos));
//                }
//            } catch (JSONException e) {
//                Log.e("Paso","Algo");
//                e.printStackTrace();
//            }
//
//            Log.e("ResultadocustomList", String.valueOf(customList));
//            return customList;
//        }
//        protected void onPostExecute(ArrayList<ExpandableListParent> customList) {
//            if(customList == null){
//                Log.d("ERORR", "No result to show.");
//                return;
//            }
//            ListDrawer(customList);
//        }
//    }
//    public void ListDrawer(ArrayList<ExpandableListParent> customList) {
//        adapter = new ExpandListAdapter(getActivity().getApplicationContext(), customList);
//        lv.setAdapter(adapter);}
//
//
//    }