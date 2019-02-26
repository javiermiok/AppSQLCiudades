package com.example.appsqlciudades.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appsqlciudades.R;
import com.example.appsqlciudades.model.Ciudad;

import java.util.ArrayList;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder> implements View.OnClickListener {

    private ArrayList<Ciudad> lista;
    private View.OnClickListener listener;

    /*-----------------------------    CONSTRUCTOR    -----------------------------*/
    public CiudadAdapter(ArrayList<Ciudad> lista) {
        this.lista = lista;
    }

    /*--------------------               METODOS OVERRIDE              -----------------------------*/
    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ciudad_item, viewGroup, false);
        v.setOnClickListener(this);
        CiudadViewHolder cvh = new CiudadViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder ciudadViewHolder, int i) {
        ciudadViewHolder.bindCiudad(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    /*--------------------               METODOS LISTENER               -----------------------------*/
    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    /*--------------------               CLASE INTERNA               -----------------------------*/
    public static class CiudadViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvProvincia;
        private TextView tvHabitantes;


        public CiudadViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreItem);
            tvProvincia = itemView.findViewById(R.id.tvProvinciaItem);
            tvHabitantes = itemView.findViewById(R.id.tvHabitantesItem);
        }

        public void bindCiudad(Ciudad c) {
            tvNombre.setText(c.getNombre());
            tvProvincia.setText(c.getProvincia());
            tvHabitantes.setText(String.valueOf(c.getHabitantes()));
        }
    }

}
