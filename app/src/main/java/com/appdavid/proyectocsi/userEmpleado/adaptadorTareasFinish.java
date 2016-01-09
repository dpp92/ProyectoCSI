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
public class adaptadorTareasFinish extends ArrayAdapter<TareasFinish> {

    int groupid;
    ArrayList<TareasFinish> tareasFinishArrayList;
    Context context;

    public adaptadorTareasFinish(Context context, int vg, int id, ArrayList<TareasFinish> tareasFinishs) {
        super(context, vg, id,tareasFinishs);

        groupid = vg;
        this.tareasFinishArrayList = tareasFinishs;
        this.context = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(groupid, parent, false);

        TextView textName = (TextView) itemView.findViewById(R.id.nameTareaFinish);
        textName.setText(tareasFinishArrayList.get(position).getName());


        TextView textPrice = (TextView) itemView.findViewById(R.id.descriptionTareaFinish);

        textPrice.setText(tareasFinishArrayList.get(position).getDescription());



        return itemView;

    }

}
