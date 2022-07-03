package com.example.miheladera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorCustom extends RecyclerView.Adapter<AdaptadorCustom.MyViewHolder> {
    private Context contexto;
    private ArrayList id_producto, nombre_producto, marca_producto, codigo_producto;

    AdaptadorCustom(Context contexto, ArrayList id_producto, ArrayList nombre_producto, ArrayList marca_producto, ArrayList codigo_producto){
        this.contexto = contexto;
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.marca_producto = marca_producto;
        this.codigo_producto = codigo_producto;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(contexto);
        View view = inflador.inflate(R.layout.mi_fila, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_producto.setText(String.valueOf(id_producto.get(position)));
        holder.nombre_producto.setText(String.valueOf(nombre_producto.get(position)));
        holder.marca_producto.setText(String.valueOf(marca_producto.get(position)));
        holder.codigo_producto.setText(String.valueOf(codigo_producto.get(position)));
    }

    @Override
    public int getItemCount() {
        System.out.println(id_producto.size());
        return id_producto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_producto, nombre_producto, marca_producto, codigo_producto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_producto = itemView.findViewById(R.id.id_producto);
            nombre_producto = itemView.findViewById(R.id.nombre_producto);
            marca_producto = itemView.findViewById(R.id.marca_producto);
            codigo_producto = itemView.findViewById(R.id.codigo_producto);

        }
    }
}
