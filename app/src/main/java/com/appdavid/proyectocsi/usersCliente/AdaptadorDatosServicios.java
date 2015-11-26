package com.appdavid.proyectocsi.usersCliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;

import java.util.ArrayList;

/**
 * Created by DAVID PUC POOT on 11/11/2015.
 */
public class AdaptadorDatosServicios extends ArrayAdapter<DatosServicios> {


    int groupid;
    ArrayList<DatosServicios> serviciosArrayList;
    Context context;


    public AdaptadorDatosServicios(Context context, int vg, int id, ArrayList<DatosServicios> serviciosArrayList) {
        super(context, vg, id, serviciosArrayList);

        this.context = context;

        groupid = vg;

        this.serviciosArrayList = serviciosArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textName = (TextView) itemView.findViewById(R.id.name);

        

        textName.setText(serviciosArrayList.get(position).getName());

        TextView textPrice = (TextView) itemView.findViewById(R.id.description);

        textPrice.setText(serviciosArrayList.get(position).getDescription());



        return itemView;

    }

}
