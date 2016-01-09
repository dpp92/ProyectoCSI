package com.appdavid.proyectocsi.userAdmin;

/**
 * Created by dpp on 8/12/15.
// */
//public class CrearProducto extends AppCompatActivity{
//    private EditText titulo,precio,descripcion,stock;
//    private Button addProducto;
//    private HttpURLConnection urlConnection;
//    private ArrayList<datosHijosAdmin> hijos;
//    private ArrayList<adminExpandableListParent> customList = new ArrayList<>();
//    private BaseExpandableListAdapter adapter;
//
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.admin_layout_productos);
//
//        inicializarComponentes();
//    }
//
//    private void inicializarComponentes() {
//        titulo =(EditText)findViewById(R.id.title_producto);
//        precio = (EditText)findViewById(R.id.precio_producto);
//        descripcion = (EditText)findViewById(R.id.descripcion_producto);
//        stock = (EditText)findViewById(R.id.stock);
//
//    }
//
//    public void addProducto(View view) {
//        String title = titulo.getText().toString().trim();
//        Double price = Double.parseDouble(precio.getText().toString().trim());
//        String describe = descripcion.getText().toString().trim();
//        int cant = Integer.parseInt(stock.getText().toString().trim());
//
//        new crear_Producto().execute(title,price,describe,cant);
//    }
//
//    private class crear_Producto extends AsyncTask{
//        @Override
//        protected Object doInBackground(Object[] objects) {
//
//            //Guardamos los parametros recibidos en varaibles reconocibles
//            String response;
//            String title = String.valueOf(objects[0]);
//            String price = String.valueOf(objects[1]);
//            String describe = String.valueOf(objects[2]);
//            String stock = String.valueOf(objects[3]);
//            String tipo = "Producto";
//
//            try {
//                URL myUrl = new URL("http:192.52.0.1");
//                urlConnection = (HttpURLConnection) myUrl.openConnection();
//                urlConnection.setDoOutput(true);
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setReadTimeout(5 * 1000);
//
//                HashMap<String, String> datos = new HashMap<>();
//                datos.put("title", title);
//                datos.put("price", price);
//                datos.put("describe", describe);
//                datos.put("edo", stock);
//
//                hijos.add(new datosHijosAdmin(title, price, describe, stock));
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
//            Toast.makeText(getApplication().getApplicationContext(),"Item agregado",Toast.LENGTH_SHORT).show();
//            clear();
//            Intent intent = new Intent("com.appdavid.proyectocsi.MainAdmin");
//            startActivity(intent);
//        }
//
//    }//fin clase asyntask
//
//    private void clear(){
//        titulo.setText("");
//        precio.setText("");
//        descripcion.setText("");
//        stock.setText("");
//    }
//
//}//fin clase main