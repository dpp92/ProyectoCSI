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
public class AdapDatosProductosAdmin extends ArrayAdapter<DatosProductosAdmin> {

    int groupid;
    ArrayList<DatosProductosAdmin> productosAdminArrayList;
    Context context;

    public AdapDatosProductosAdmin (Context context,int vg,int id,ArrayList<DatosProductosAdmin>productosAdminArrayList) {
        super(context, vg, id, productosAdminArrayList);
        this.context = context;
        this.productosAdminArrayList = productosAdminArrayList;
        groupid = vg;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        DatosProductosAdmin productos = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        //ImageView imageThumbnail = (ImageView)itemView.findViewById(R.id.image_adminProductos);

       // imageThumbnail.setImageResource(Integer.parseInt(productos.ThumbnailResource));

        TextView textName = (TextView) itemView.findViewById(R.id.name_adminProductos);

        textName.setText(productosAdminArrayList.get(position).getNombre());

        TextView textPrice = (TextView) itemView.findViewById(R.id.description_adminProductos);

        textPrice.setText(productosAdminArrayList.get(position).getDescripcion());



        return itemView;

    }

}
