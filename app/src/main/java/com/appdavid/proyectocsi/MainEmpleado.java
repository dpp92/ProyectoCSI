package com.appdavid.proyectocsi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.appdavid.proyectocsi.userEmpleado.FragmentHomeEmpleado;
import com.appdavid.proyectocsi.userEmpleado.adapFragmentEmpleado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAVID PUC POOT on 12/11/2015.
 */
public class MainEmpleado extends AppCompatActivity{


    adapFragmentEmpleado adapfragmentEmpleado;
    ViewPager mviewPager;
    private List<Fragment> fragmentsEmpleado;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_fragment_layout);
        Log.e("Sesion", "Entramos a MainEmpleado");

        //Creamos la lista
        fragmentsEmpleado = new ArrayList<Fragment>();
        fragmentsEmpleado.add(new FragmentHomeEmpleado());

        adapfragmentEmpleado = new adapFragmentEmpleado(getSupportFragmentManager(),fragmentsEmpleado);
        mviewPager = (ViewPager)findViewById(R.id.viewPagerEmpleado);
        mviewPager.setAdapter(adapfragmentEmpleado);


    }



}
