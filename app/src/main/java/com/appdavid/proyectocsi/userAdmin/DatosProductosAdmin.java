package com.appdavid.proyectocsi.userAdmin;

/**
 * Created by dpp on 11/12/15.
 */
public class DatosProductosAdmin {
    private int id;
    private String nombre;
    private String descripcion;
    public String ThumbnailResource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getThumbnailResource() {
        return ThumbnailResource;
    }

    public void setThumbnailResource(String thumbnailResource) {
        ThumbnailResource = thumbnailResource;
    }
}
