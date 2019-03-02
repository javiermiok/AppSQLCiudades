package com.example.appsqlciudades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appsqlciudades.db.CiudadDatasource;
import com.example.appsqlciudades.model.Ciudad;

public class EditarActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etProvincia;
    private EditText etHabitantes;

    private long id;
    private Ciudad ciudad;

    private CiudadDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        etNombre = findViewById(R.id.etNombreEdit);
        etProvincia = findViewById(R.id.etProvinciaEdit);
        etHabitantes = findViewById(R.id.etHabitantesEdit);

        // RECOGEMOS EL ID DE LA CIUDAD SELECCIONADA
        id = getIntent().getLongExtra("SELECCION", -1);
        cds = new CiudadDatasource(this);
        ciudad = cds.consultarCiudad(id);

        if (ciudad != null) {
            etNombre.setText(ciudad.getNombre());
            etProvincia.setText(ciudad.getProvincia());
            etHabitantes.setText(String.valueOf(ciudad.getHabitantes()));
        }
    }

    /**
     * Comprueba si se ha cambiado el dato de habitantes
     *
     * Intenta modificar la ciudad
     * Devuelve a la actividad anterior el resultado de la operación
     * @param v
     */
    public void modificar(View v) {

        String hab = etHabitantes.getText().toString().trim();

        if (hab.equals(String.valueOf(ciudad.getHabitantes()))) {
            Toast.makeText(this, "No has cambiado el número de habitantes",Toast.LENGTH_LONG).show();
        } else {
            ciudad.setHabitantes(Long.parseLong(hab));
            int result = cds.modificarCiudad(ciudad);

            if(result == 1) {
                setResult(RESULT_OK, getIntent().putExtra("PROCESO", "MOD"));
            } else {
                setResult(RESULT_OK, getIntent().putExtra("PROCESO", "MOD_ERROR"));
            }
            finish();
        }

    }

    /**
     * Intenta borrar la ciudad
     * Retorna un string con el exíto o fracaso de la tarea
     * @param v
     */
    public void borrar(View v) {

        int result = cds.borrarCiudad(ciudad.getId());

        if(result == 1) {
            setResult(RESULT_OK, getIntent().putExtra("PROCESO", "BORRAR"));
        } else {
            setResult(RESULT_OK, getIntent().putExtra("PROCESO", "BORRAR_ERROR"));
        }

        finish();
    }

    public void volverEdit(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
