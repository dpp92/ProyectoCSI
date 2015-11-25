package com.appdavid.proyectocsi.userEmpleado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appdavid.proyectocsi.R;

/**
 * Created by dpp on 24/11/15.
 */
public class VistaTrabajos extends AppCompatActivity {

    TextView titulo;
    EditText info;
    int long_info, long_info_update;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_tareas);

        titulo = (TextView)findViewById(R.id.titulo);
        info=(EditText)findViewById(R.id.info_tareas);
        //recibimos los datos
        bundle=getIntent().getExtras();
        Toast.makeText(this,bundle.getString("tipo"),Toast.LENGTH_LONG).show();
        titulo.setText(bundle.getString("titulo"));
        info.setText(bundle.getString("contenido"));

        info.setEnabled(false);
        info.setTextColor(getResources().getColor(R.color.color));




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
                break;
            case R.id.save_tasks:
                long_info_update = info.length();
                if (long_info!=long_info_update){
                    actualizarTrabajo(bundle);
                }
                break;



        }

        return super.onOptionsItemSelected(item);
    }

    private void actualizarTrabajo(Bundle bundle) {



        Toast.makeText(getBaseContext(),"Aqui va a pasar algo",Toast.LENGTH_LONG).show();
    }
}
