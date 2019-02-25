package com.example.appsqlciudades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alta(View v) {
        Intent i = new Intent(this, AltaActivity.class);
        startActivity(i);
    }

    public void consultar(View v) {
        Intent i = new Intent(this, ConsultarActivity.class);
        startActivity(i);
    }
}
