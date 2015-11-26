package com.appdavid.proyectocsi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.appdavid.proyectocsi.usersCliente.FragmentProductos;
import com.appdavid.proyectocsi.usersCliente.FragmentServicios;
import com.appdavid.proyectocsi.usersCliente.adapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAVID PUC POOT on 10/11/2015.
 */
public class LogedActivity extends FragmentActivity {

    adapFragment mPagerAdapter;
    ViewPager mviewPager;
    private List<Fragment> listaFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_layout_usuario);
        Log.e("Sesion","Entramos a LogedActivity");

        // Creamos una lista de Fragments
        listaFragments = new ArrayList<Fragment>();
        listaFragments.add(new FragmentServicios());
        listaFragments.add(new FragmentProductos());

        // Creamos nuestro Adapter
        mPagerAdapter = new adapFragment(getSupportFragmentManager(),
                listaFragments);

        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) findViewById(R.id.viewPager);

        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);
    }
}