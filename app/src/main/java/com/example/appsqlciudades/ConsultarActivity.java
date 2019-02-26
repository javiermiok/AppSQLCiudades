package com.example.appsqlciudades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.appsqlciudades.db.CiudadDatasource;
import com.example.appsqlciudades.model.Ciudad;
import com.example.appsqlciudades.recycler.CiudadAdapter;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    private RecyclerView rv;
    private CiudadAdapter adapter;
    private LinearLayoutManager llm;
    private ArrayList<Ciudad> datos;
    private CiudadDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        rv = findViewById(R.id.rvLista);

        cds = new CiudadDatasource(this);
        datos = cds.consultarCiudades();

        if(datos.isEmpty()) {
            Toast.makeText(this,"No se han encontrado datos", Toast.LENGTH_LONG).show();
        } else {
            adapter = new CiudadAdapter(datos);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO borrar
                    String msg = "Seleccionada la opción " + rv.getChildAdapterPosition(v) ;
                    Toast.makeText(ConsultarActivity.this,msg,Toast.LENGTH_SHORT).show();

                    //Pasar a la ventana Editar
                    Intent i = new Intent(ConsultarActivity.this, EditarActivity.class);
                    i.putExtra("SELECCION", datos.get(rv.getChildAdapterPosition(v)));
                    startActivity(i);
                }
            });
            llm = new LinearLayoutManager(this);

            rv.setLayoutManager(llm);
            rv.setAdapter(adapter);
        }
    }

    public void volver(View v) {
        finish();
    }
}
