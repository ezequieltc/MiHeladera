package com.example.miheladera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton abrirventana;
    Button botonListado, botonConsultar, botonActualizar, botonPrueba;
    ConstraintLayout soporte;
    int id_aleatoria = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abrirventana = findViewById(R.id.floatingActionButton2);
        abrirventana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VentanaAgregar.class);
                startActivity(intent);
            }
        });
        botonListado = findViewById(R.id.buttonListado);
        botonListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Listado.class);
                startActivity(intent);

            }
        });
        botonConsultar = findViewById(R.id.buttonBuscar);
        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VentanaBuscar.class);
                startActivity(intent);
            }
        });
        botonActualizar = findViewById(R.id.buttonActualizar);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VentanaActualizar.class);
                startActivity(intent);
            }
        });
//        soporte = findViewById(R.id.soporte);
//        botonPrueba = findViewById(R.id.buttonPrueba);
//        botonPrueba.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                id_aleatoria++;
//                int estilo = androidx.constraintlayout.widget.R.style.Base_Widget_AppCompat_Button;
//                Button prueba = new Button(new ContextThemeWrapper(soporte.getContext(), estilo), null, estilo);
//                prueba.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
//                prueba.setText("Button" + id_aleatoria);
//                prueba.setId(id_aleatoria);
//                soporte.addView(prueba);
//                prueba.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "PUTO", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
    }

}