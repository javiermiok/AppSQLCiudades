package com.example.appsqlciudades;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.appsqlciudades.db.CiudadDatasource;
import com.example.appsqlciudades.model.Ciudad;
import com.example.appsqlciudades.recycler.CiudadAdapter;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    public static final int COD_REQ = 1;

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
                    i.putExtra("SELECCION", datos.get(rv.getChildAdapterPosition(v)).getId());
                    i.putExtra("POSIC_RV", rv.getChildAdapterPosition(v));
                    startActivityForResult(i, COD_REQ);
                }
            });
            llm = new LinearLayoutManager(this);

            rv.setLayoutManager(llm);
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == COD_REQ) {
            if(resultCode == RESULT_OK) {

                //RECOGEMOS EL RESULTADO QUE SE HA ENVIADO
                String resultado = data.getStringExtra("PROCESO");
                int pos = data.getIntExtra("POSIC_RV",-1);

                switch (resultado) {

                    case "MOD":

                        datos.set(pos, cds.consultarCiudad(data.getLongExtra("SELECCION",-1)));
                        adapter.notifyItemChanged(pos);

                        mostarMensaje("Se ha modificado con éxito");
                        break;

                    case "MOD_ERROR":
                        mostarMensaje("Ha habido un problema al intentar modificar");
                        break;

                    case "BORRAR":
                        rv.removeViewAt(pos);
                        datos.remove(pos);
                        mostarMensaje("Se ha borrado con éxito" +pos);
                        break;

                    case "BORRAR_ERROR":
                        mostarMensaje("Ha habido un problema al intentar borrar");
                        break;

                    default:
                        mostarMensaje("No se ha retornado correctamente");

                }


            } else if (resultCode == RESULT_CANCELED) {
                // SI CANCELA NO SE HACE NADA
            }
        }

    }

    public void volver(View v) {
        finish();
    }

    private void mostarMensaje(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }
}
