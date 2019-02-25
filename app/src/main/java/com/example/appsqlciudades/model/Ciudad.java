package com.example.appsqlciudades.model;

import java.io.Serializable;

public class Ciudad implements Serializable {

    private long id;
    private String nombre;
    private String provincia;
    private long habitantes;

    public Ciudad(long id, String nombre, String provincia, long habitantes) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.habitantes = habitantes;
    }

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
}
