package com.example.appsqlciudades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsqlciudades.db.CiudadDatasource;
import com.example.appsqlciudades.model.Ciudad;

public class AltaActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etProvincia;
    private EditText etHabitantes;

    private CiudadDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etNombre = findViewById(R.id.etNombreAlta);
        etProvincia = findViewById(R.id.etProvinciaAlta);
        etHabitantes = findViewById(R.id.etHabitantesAlta);

        cds = new CiudadDatasource(this);
    }

    public void guardarAlta(View v) {
        String nombre = etNombre.getText().toString().trim();
        String provincia = etProvincia.getText().toString().trim();
        String habitantes = etHabitantes.getText().toString().trim();

        if(nombre.isEmpty() || provincia.isEmpty() || habitantes.isEmpty()) {
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

        } else {
            Ciudad c = new Ciudad(nombre, provincia, Long.parseLong(habitantes));
            long id = cds.insertarCiudad(c);

            if(id != -1) {
                Toast.makeText(this, "Se ha insertado el nuevo contacto con id: " +id, Toast.LENGTH_LONG).show();
                c.setId((int) id);

                etNombre.setText("");
                etProvincia.setText("");
                etHabitantes.setText("");
            } else {
                Toast.makeText(this, "No se ha insertado el nuevo contacto", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void cancelarAlta(View v) {
        finish();
    }
}
