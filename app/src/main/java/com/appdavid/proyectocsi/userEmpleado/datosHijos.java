package com.appdavid.proyectocsi.userEmpleado;

/**
 * Created by dpp on 17/11/15.
 */
public class datosHijos {

    private String titulo;
    private String contenido;

    public datosHijos(String title, String contenido) {
        this.titulo = title;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
