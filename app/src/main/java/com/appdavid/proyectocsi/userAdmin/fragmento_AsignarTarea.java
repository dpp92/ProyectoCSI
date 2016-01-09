package com.appdavid.proyectocsi.userAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdavid.proyectocsi.R;

/**
 * Created by dpp on 11/12/15.
 */
public class fragmento_AsignarTarea extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_layout_asignartarea,container,false);

        return rootView;
    }
}
