package com.appdavid.proyectocsi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.appdavid.proyectocsi.userAdmin.adapFragmentAdmin;
import com.appdavid.proyectocsi.userAdmin.fragmento_AsignarTarea;
import com.appdavid.proyectocsi.userAdmin.fragmento_DatosProductos_Admin;
import com.appdavid.proyectocsi.userAdmin.fragmento_DatosServicios_Admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpp on 26/11/15.
// */
public class MainAdmin extends AppCompatActivity {

    public static final String TAG_ACTIVITY ="MainAdmin";

    adapFragmentAdmin adapfragmentAdmin;
    ViewPager mviewPager;
    private List<Fragment> fragmentsAdmin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_fragment_layout);
        Log.e("Sesion", TAG_ACTIVITY);

        //Creamos la lista
        fragmentsAdmin = new ArrayList<Fragment>();
        fragmentsAdmin.add(new fragmento_DatosProductos_Admin());
        fragmentsAdmin.add(new fragmento_DatosServicios_Admin());
        fragmentsAdmin.add(new fragmento_AsignarTarea());

        adapfragmentAdmin = new adapFragmentAdmin(getSupportFragmentManager(),fragmentsAdmin);
        mviewPager = (ViewPager)findViewById(R.id.viewPagerAdmin);
        mviewPager.setAdapter(adapfragmentAdmin);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
