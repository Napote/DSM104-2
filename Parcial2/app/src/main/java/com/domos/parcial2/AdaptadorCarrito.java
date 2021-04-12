package com.domos.parcial2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.domos.parcial2.datos.Item;
import com.domos.parcial2.datos.Medicamento;

import java.util.List;

public class AdaptadorCarrito extends ArrayAdapter<Item> {


    List<Item> itemsCarrito;
    private Activity context;

    public AdaptadorCarrito(@NonNull Activity context, @NonNull List<Item> itemsCarrito){
        super(context, R.layout.medicamento_layout, itemsCarrito);
        this.context = context;
        this.itemsCarrito = itemsCarrito;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.item_carrito,null);
        else rowview = view;

        TextView txtNombreMedicamento= rowview.findViewById(R.id.txtNombreProducto);
        TextView txtUnidades = rowview.findViewById(R.id.tvNumeroUnidades);
        TextView txtCosto = rowview.findViewById(R.id.txtCostoProducto);
        TextView txtQuitarItem = rowview.findViewById(R.id.tvQuitar);


        ImageView imgMedicamento = rowview.findViewById(R.id.imgMedicamentoCarrito);

        ImageButton agregarUnidad = rowview.findViewById(R.id.ibtnAgregarUnidades);
        ImageButton quitarUnidad = rowview.findViewById(R.id.ibtnQuitarUnidades);

        txtNombreMedicamento.setText(itemsCarrito.get(position).getNombre());
        txtUnidades.setText(""+itemsCarrito.get(position).getUnidades());
        txtCosto.setText("$ "+String.format("%.2f", itemsCarrito.get(position).getCosto()));
        imgMedicamento.setImageResource(itemsCarrito.get(position).getFoto());
        txtQuitarItem.setText("Quitar");

        agregarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int unidades = itemsCarrito.get(position).getUnidades();
                if(unidades < 5){
                    unidades=unidades+1;
                    itemsCarrito.get(position).setUnidades(unidades);
                    itemsCarrito.get(position).recalcularCostoUnidades();

                    //Este intent desencadena un evento en la clase Carrito que actualiza la cantidad y el total de elementos mostrados
                    Intent intent = new Intent("actualizarCarrito");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    Carrito.adapter.notifyDataSetChanged();
                }
            }
        });

        quitarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int unidades = itemsCarrito.get(position).getUnidades();
                if(unidades > 1){
                    unidades=unidades-1;
                    itemsCarrito.get(position).setUnidades(unidades);
                    itemsCarrito.get(position).recalcularCostoUnidades();

                    Intent intent = new Intent("actualizarCarrito");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    Carrito.adapter.notifyDataSetChanged();
                }

            }
        });

        txtQuitarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsCarrito.remove(position);

                Intent intent = new Intent("actualizarCarrito");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                Carrito.adapter.notifyDataSetChanged();
        }});

        return rowview;
    }
}
