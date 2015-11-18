package com.appdavid.proyectocsi.usersCliente;

/**
 * Created by DAVID PUC POOT on 12/11/2015.
 */
public class DatosProductos {



    public String ThumbnailResource;
    private String name;
    private String description;
    public String getThumbnailResource() {
        return ThumbnailResource;
    }

    public void setThumbnailResource(String thumbnailResource) {
        ThumbnailResource = thumbnailResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
