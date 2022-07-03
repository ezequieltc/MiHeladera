package com.example.miheladera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VentanaAgregar extends AppCompatActivity {
    EditText NombreProducto, CodigoBarra, MarcaProducto, Cantidad;
    Button buttonAgregar;
    FloatingActionButton buttonScanner;
    int segundaActividad = 1;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_agregar);
        NombreProducto = findViewById(R.id.editTextNombreProducto);
        CodigoBarra = findViewById(R.id.editTextCodigoBarra);
        MarcaProducto = findViewById(R.id.editTextMarcaProducto);
        Cantidad = findViewById(R.id.editTextCantidad);
        buttonAgregar = findViewById(R.id.buttonBuscar);
        buttonScanner = findViewById(R.id.floatingActionButtonScanner);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos myDB = new BaseDeDatos(VentanaAgregar.this);
                myDB.agregarProducto(NombreProducto.getText().toString().trim(), MarcaProducto.getText().toString().trim(),
                            Long.valueOf(CodigoBarra.getText().toString().trim()),Integer.valueOf(Cantidad.getText().toString().trim()));
                NombreProducto.setText("");
                CodigoBarra.setText("");
                MarcaProducto.setText("");
                Cantidad.setText("");

            }
        });
        buttonScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasPermission(VentanaAgregar.this, Manifest.permission.CAMERA)) {
                    startActivityForResult(new Intent(VentanaAgregar.this, Scanner.class), segundaActividad);
                }
                else{
                    ActivityCompat.requestPermissions(VentanaAgregar.this,new String[]{Manifest.permission.CAMERA},102);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == segundaActividad) {
            if(resultCode == Activity.RESULT_OK){
                resultado=data.getStringExtra("resultado");
                CodigoBarra.setText(resultado);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    } //onActivityResult
    private boolean hasPermission(Context context, String permission){
        return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivityForResult(new Intent(VentanaAgregar.this, Scanner.class), segundaActividad);
        }
        else{
            Toast.makeText(this, "Acceso Requerido", Toast.LENGTH_SHORT).show();
        }
    }
}