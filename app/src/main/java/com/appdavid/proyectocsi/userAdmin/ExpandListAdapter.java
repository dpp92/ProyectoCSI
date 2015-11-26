package com.appdavid.proyectocsi.userAdmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.appdavid.proyectocsi.R;
import com.appdavid.proyectocsi.userEmpleado.VistaTrabajos;

import java.util.ArrayList;

/**
 * Created by dpp on 26/11/15.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {


    public ExpandListAdapter(Context context, ArrayList<ExpandableListParent> groups) {
        this.context = context;
        this.groups = groups;
    }

    private Context context;
    private ArrayList<ExpandableListParent> groups;

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ExpandableListParent group = (ExpandableListParent) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.admin_layout_items, null);
        }

        ((CheckedTextView) convertView).setText(group.getTitle());
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final datosHijos child = (datosHijos) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.admin_layout_sub_items, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.admintextViewsub);
        tv.setText(child.getTitulo());
        final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.adminCheckbox);
        checkBox.setEnabled(false);
        final View finalConvertView = convertView;
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                checkBox.setEnabled(true);
                checkBox.setChecked(isChildSelectable(groupPosition,childPosition));

            return false;
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vistaTrabajos = new Intent(context,VistaTrabajos.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                vistaTrabajos.putExtra("id",child.getId());
                vistaTrabajos.putExtra("tipo",child.getTipo());
                vistaTrabajos.putExtra("titulo",child.getTitulo());
                vistaTrabajos.putExtra("contenido",child.getContenido());
                context.startActivity(vistaTrabajos);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
