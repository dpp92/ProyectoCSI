package com.appdavid.proyectocsi.userAdmin;

/**
 * Created by dpp on 26/11/15.
 */
public class datosHijos {

    private int id;
    private String tipo;
    private String titulo;
    private String contenido;

    public datosHijos(int id, String tipo, String titulo, String contenido) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
