package com.example.miheladera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {
    RecyclerView recyclerView;
    BaseDeDatos myDB;
    ArrayList<String> id_producto, nombre_producto, marca_producto, codigo_producto;
    AdaptadorCustom adaptadorCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        recyclerView = findViewById(R.id.reciclador);
        myDB = new BaseDeDatos(Listado.this);
        id_producto = new ArrayList<>();
        nombre_producto = new ArrayList<>();
        marca_producto = new ArrayList<>();
        codigo_producto = new ArrayList<>();
        mostrarData();
        adaptadorCustom = new AdaptadorCustom(Listado.this, id_producto, nombre_producto, marca_producto, codigo_producto);
        recyclerView.setAdapter(adaptadorCustom);
        recyclerView.setLayoutManager(new LinearLayoutManager(Listado.this));
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Puto");
            }
        });

    }

    void mostrarData() {
        Cursor cursor = myDB.leerData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay informacion", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id_producto.add(cursor.getString(0));
                codigo_producto.add(cursor.getString(1));
                nombre_producto.add(cursor.getString(2));
                marca_producto.add(cursor.getString(3));
            }

        }
    }
}