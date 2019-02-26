package com.example.appsqlciudades.model;

import java.io.Serializable;

public class Ciudad implements Serializable {

    private long id;
    private String nombre;
    private String provincia;
    private long habitantes;

    public Ciudad(String nombre, String provincia, long habitantes) {
        id = -1;
        this.nombre = nombre;
        this.provincia = provincia;
        this.habitantes = habitantes;
    }

    public Ciudad(long id, String nombre, String provincia, long habitantes) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.habitantes = habitantes;
    }


    /*********************                     GETTERS                     ************************/
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public long getHabitantes() {
        return habitantes;
    }

    /*********************                     SETTERS                     ************************/
    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setHabitantes(long habitantes) {
        this.habitantes = habitantes;
    }
}
