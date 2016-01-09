package com.appdavid.proyectocsi.userAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;

import java.util.ArrayList;

/**
 * Created by dpp on 11/12/15.
 */
public class AdapDatosServiciosAdmin extends ArrayAdapter<DatosServiciosAdmin> {

    int groupid;
    ArrayList<DatosServiciosAdmin> serviciosAdminArrayList;
    Context context;

    public AdapDatosServiciosAdmin (Context context,int vg,int id,ArrayList<DatosServiciosAdmin>serviciosAdminArrayList) {
        super(context, vg, id, serviciosAdminArrayList);
        this.context = context;
        this.serviciosAdminArrayList = serviciosAdminArrayList;
        groupid = vg;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        DatosServiciosAdmin serviciosAdmin = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

//        ImageView imageThumbnail = (ImageView)itemView.findViewById(R.id.image_adminServicios);
//
//        imageThumbnail.setImageResource(Integer.parseInt(serviciosAdmin.ThumbnailResource));

        TextView textName = (TextView) itemView.findViewById(R.id.name_adminServicios);

        textName.setText(serviciosAdminArrayList.get(position).getNombre());

        TextView textPrice = (TextView) itemView.findViewById(R.id.description_adminServicios);

        textPrice.setText(serviciosAdminArrayList.get(position).getDescripcion());



        return itemView;

    }

}

