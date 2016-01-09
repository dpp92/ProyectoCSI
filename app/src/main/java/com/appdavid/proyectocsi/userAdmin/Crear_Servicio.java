package com.appdavid.proyectocsi.userAdmin;

/**
 * Created by dpp on 7/12/15.
// */
//public class Crear_Servicio extends AppCompatActivity {
//    //Declarando las variables
//    ArrayList<datosHijosAdmin> hijos;
//    ArrayList<adminExpandableListParent> customList = new ArrayList<>();
//    public BaseExpandableListAdapter adapter;
//    private Button agregarServicio;
//    private EditText titulo, precio, descripcion, estado;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.admin_layout_servicios);
//        inicializarComponentes();
//
//       getActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
//
//    private void inicializarComponentes() {
//        agregarServicio = (Button) findViewById(R.id.add_servicio);
//        titulo = (EditText) findViewById(R.id.title_servicio);
//        precio = (EditText) findViewById(R.id.precio_servicio);
//        descripcion = (EditText) findViewById(R.id.descripcion_servicios);
//        estado = (EditText) findViewById(R.id.edo_servicio);
//
//    }
//
//    public void addServicio(View view) {
//        String title, describe, edo;
//        Double price;
//
//        title = titulo.getText().toString().trim();
//        price = Double.parseDouble(precio.getText().toString().trim());
//        describe = descripcion.getText().toString().trim();
//        edo = estado.getText().toString().trim();
//
//        new crearServicio().execute(title, price, describe, edo);
//    }
//
//
//    private class crearServicio extends AsyncTask {
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            //Guardamos los parametros recibidos en varaibles reconocibles
//            String response;
//            String title = String.valueOf(objects[0]);
//            String price = String.valueOf(objects[1]);
//            String describe = String.valueOf(objects[2]);
//            String edo = String.valueOf(objects[3]);
//            String tipo = "Servicio";
//
//            try {
//                URL myUrl = new URL("http:192.52.0.1");
//                HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection();
//                urlConnection.setDoOutput(true);
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setReadTimeout(5 * 1000);
//
//                HashMap<String, String> datos = new HashMap<>();
//                datos.put("title", title);
//                datos.put("price", price);
//                datos.put("describe", describe);
//                datos.put("edo", edo);
//
//                hijos.add(new datosHijosAdmin(title, price, describe, edo));
//                customList.add(new adminExpandableListParent(tipo, hijos));
//
//                //convertipos los datosMap a estructura reconocible por el server
//                StringBuilder cadena = new StringBuilder();
//                boolean first = true;
//                for (Map.Entry<String, String> entry : datos.entrySet()) {
//                    if (first) {
//                        first = false;
//                    } else {
//                        cadena.append(" & ");
//                    }
//                    cadena.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//                    cadena.append("=");
//                    cadena.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//                }
//                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
//                Log.e("POST", cadena.toString());
//                out.write(cadena.toString());
//                out.close();
//
//                //solicitamos la respuesta del server
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(urlConnection.getInputStream()));
//
//                while ((response = in.readLine()) != null) {
//
//                    if (!response.equals("actualizado")) {
//                        Log.e("Error al actualizar", response);
//                        cancel(true);
//                    }
//                }
//
//                in.close();
//            } catch (Exception e) {
//                Log.e("ErrorCrear_Servicio", String.valueOf(e));
//            }
//            return null;
//        }
//
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            adapter = new adminExpandableListAdapter(getBaseContext(), customList);
//            adapter.notifyDataSetChanged();
//            clear();
//            Intent intent = new Intent("com.appdavid.proyectocsi.MainAdmin");
//            startActivity(intent);
//        }
//
//    }//fin clase asyntask
//    private void clear(){
//        titulo.setText("");
//        precio.setText("");
//        descripcion.setText("");
//        estado.setText("");
//    }
//
//}//fin clase main
