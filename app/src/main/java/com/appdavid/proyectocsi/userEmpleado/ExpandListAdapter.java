package com.appdavid.proyectocsi.userEmpleado;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;

import java.util.ArrayList;

/**
 * Created by dpp on 17/11/15.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ExpandableListParent> groups;

    public ExpandListAdapter(Context context, ArrayList<ExpandableListParent> groups) {

        this.context = context;
        this.groups = groups;
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpandableListParent group = (ExpandableListParent) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.layout_empleado_items, null);
        }

        ((CheckedTextView) convertView).setText(group.getTitle());
        ((CheckedTextView) convertView).setChecked(isExpanded);
        //TODO CONVERTIR TEXTO A COLOR ((CheckedTextView) convertView).setTextColor(Color.BLACK);
        /*TextView tv = (TextView) convertView.findViewById(R.id.exp_text);
        Log.e("Titulo",group.getTitle());
        tv.setText("Titulo");
        */
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final datosHijos child = (datosHijos) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_empleado_sub_items, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.textView1);
        tv.setText(child.getTitulo());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vistaTrabajos = new Intent(context,VistaTrabajos.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                vistaTrabajos.putExtra("tipo",child.getTipo());
                vistaTrabajos.putExtra("titulo",child.getTitulo());
                vistaTrabajos.putExtra("contenido",child.getContenido());
                context.startActivity(vistaTrabajos);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

