package com.appdavid.proyectocsi.userEmpleado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;

import java.util.ArrayList;

/**
 * Created by dpp on 10/12/15.
 */
public class AdaptadorTareasNuevas extends ArrayAdapter<TareasNuevas> {

    int groupid;
    ArrayList<TareasNuevas> nuevasArrayList;
    Context context;

    public AdaptadorTareasNuevas(Context context, int vg, int id, ArrayList<TareasNuevas> nuevasArrayList) {
        super(context, vg, id,nuevasArrayList);
        groupid = vg;
        this.nuevasArrayList = nuevasArrayList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textName = (TextView) itemView.findViewById(R.id.nameTareaNueva);

        textName.setText(nuevasArrayList.get(position).getName());

        TextView textPrice = (TextView) itemView.findViewById(R.id.descriptionTareaNueva);

        textPrice.setText(nuevasArrayList.get(position).getDescription());



        return itemView;

    }

}
