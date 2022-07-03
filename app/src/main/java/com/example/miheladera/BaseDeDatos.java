package com.example.miheladera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.os.IResultReceiver;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {
    private Context contexto;
    private static final String DATABASE_NAME = "Basededatos.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "mis_productos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CODIGO = "producto_codigo";
    private static final String COLUMN_NOMBRE = "producto_nombre";
    private static final String COLUMN_MARCA = "producto_marca";
    private static final String COLUMN_CANTIDAD = "producto_cantidad";


    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CODIGO + " INTEGER, "+
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_MARCA + " TEXT, " +
                COLUMN_CANTIDAD + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void agregarProducto(String producto_nombre, String producto_marca, long producto_codigo, int producto_cantidad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE, producto_nombre);
        cv.put(COLUMN_MARCA, producto_marca);
        cv.put(COLUMN_CODIGO, producto_codigo);
        cv.put(COLUMN_CANTIDAD, producto_cantidad);

        long resultado = db.insert(TABLE_NAME, null, cv);
        if (resultado == -1){
            Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show();
            }
        else {
            Toast.makeText(contexto, "Ingresado con exito", Toast.LENGTH_SHORT).show();
            }
        }

    boolean actualizarProducto(String producto_nombre, String producto_marca, long producto_codigo, String producto_cantidad){
        SQLiteDatabase db = this.getWritableDatabase();
        String querry = "UPDATE " + TABLE_NAME + " SET producto_nombre = "+"'"
                + producto_nombre + "', producto_marca = " + "'" + producto_marca + "', producto_cantidad = " + "'" + producto_cantidad + "'" +" WHERE producto_codigo = "+ producto_codigo;
        db.execSQL(querry);
        boolean noErrores = true;
        Toast.makeText(contexto, "Item actualizado correctamente", Toast.LENGTH_SHORT).show();
        return noErrores;

    }

    Cursor leerData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
        }

    Cursor buscarDato(long producto_codigo){
        long resultado;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE producto_codigo = "+ producto_codigo;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor;
    }
    }

