package com.appdavid.proyectocsi.userAdmin;

import java.util.ArrayList;

/**
 * Created by dpp on 26/11/15.
 */
public class ExpandableListParent {
    private String title;
    private ArrayList<datosHijos> children;


    public ExpandableListParent(String parent, ArrayList<datosHijos> parentChildren) {
        this.title = parent;
        this.children = parentChildren;
    }

    public ArrayList<datosHijos> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<datosHijos> children) {
        this.children = children;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

