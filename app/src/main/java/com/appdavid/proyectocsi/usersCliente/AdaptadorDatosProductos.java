package com.appdavid.proyectocsi.usersCliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;

import java.util.ArrayList;

/**
 * Created by DAVID PUC POOT on 12/11/2015.
 */
public class AdaptadorDatosProductos extends ArrayAdapter<DatosProductos> {


    int groupid;
    ArrayList<DatosProductos> productosArrayList;
    Context context;


    public AdaptadorDatosProductos(Context context, int vg, int id, ArrayList<DatosProductos> productosArrayList) {
        super(context, vg, id, productosArrayList);

        this.context = context;

        groupid = vg;

        this.productosArrayList = productosArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        DatosProductos productos = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        ImageView imageThumbnail = (ImageView)itemView.findViewById(R.id.image);

//        imageThumbnail.setImageResource(Integer.parseInt(productos.ThumbnailResource));

        TextView textName = (TextView) itemView.findViewById(R.id.nameProductos);

        textName.setText(productosArrayList.get(position).getName());

        TextView textPrice = (TextView) itemView.findViewById(R.id.descriptionProductos);

        textPrice.setText(productosArrayList.get(position).getDescription());



        return itemView;

    }

}
