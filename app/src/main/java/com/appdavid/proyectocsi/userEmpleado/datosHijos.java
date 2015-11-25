package com.appdavid.proyectocsi.userEmpleado;

/**
 * Created by dpp on 17/11/15.
 */
public class datosHijos {

    private int id;
    private String tipo;
    private String titulo;
    private String contenido;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public datosHijos(String tipo,String title, String contenido,int id) {
        this.id   = id;
        this.tipo = tipo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
