package com.example.miheladera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VentanaActualizar extends AppCompatActivity {
    EditText NombreProducto, CodigoBarra, MarcaProducto, Cantidad;
    Button buttonActualizar;
    FloatingActionButton buttonScanner;
    int segundaActividad = 1;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_actualizar);
        NombreProducto = findViewById(R.id.editTextNombreProducto);
        CodigoBarra = findViewById(R.id.editTextCodigoBarra);
        MarcaProducto = findViewById(R.id.editTextMarcaProducto);
        Cantidad = findViewById(R.id.editTextCantidad);
        buttonActualizar = findViewById(R.id.buttonActualizar);
        buttonScanner = findViewById(R.id.floatingActionButtonScanner);
        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos myDB = new BaseDeDatos(VentanaActualizar.this);
                long longResultado=Long.parseLong(resultado);
                Cursor itemLong = myDB.buscarDato(longResultado);
                boolean noErrores = myDB.actualizarProducto(NombreProducto.getText().toString(), MarcaProducto.getText().toString(), longResultado, Cantidad.getText().toString() );
                if (noErrores == true){
                    Toast.makeText(VentanaActualizar.this, "Item Actualizado Correctamente", Toast.LENGTH_SHORT).show();
                    NombreProducto.setText("");
                    MarcaProducto.setText("");
                    CodigoBarra.setText("");
                    Cantidad.setText("");
                }
            }
        });
        buttonScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasPermission(VentanaActualizar.this, Manifest.permission.CAMERA)) {
                    startActivityForResult(new Intent(VentanaActualizar.this, Scanner.class), segundaActividad);
                }
                else{
                    ActivityCompat.requestPermissions(VentanaActualizar.this,new String[]{Manifest.permission.CAMERA},102);
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
                BaseDeDatos myDB = new BaseDeDatos(VentanaActualizar.this);
                long longResultado=Long.parseLong(resultado);
                Cursor itemLong = myDB.buscarDato(longResultado);
                if (itemLong.getCount() == 0){
                    Toast.makeText(VentanaActualizar.this, "No existe ese item", Toast.LENGTH_SHORT).show();

                }else {
                    NombreProducto.setText(itemLong.getString(2));
                    MarcaProducto.setText(itemLong.getString(3));
                    Cantidad.setText(itemLong.getString(4));
                    CodigoBarra.setText(resultado);
                    System.out.println(itemLong);
                }
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
            startActivityForResult(new Intent(VentanaActualizar.this, Scanner.class), segundaActividad);
        }
        else{
            Toast.makeText(this, "Acceso Requerido", Toast.LENGTH_SHORT).show();
        }
    }
}