package com.domos.parcial2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.domos.parcial2.datos.Pedido;

import java.util.List;

public class AdaptadorPedido extends ArrayAdapter<Pedido> {
    List<Pedido> pedidos;
    private Activity context;

    public AdaptadorPedido(@NonNull Activity context, @NonNull List<Pedido> pedidos){
        super(context, R.layout.pedido, pedidos);
        this.context = context;
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.pedido,null);
        else rowview = view;

        TextView txtFechaOrden = rowview.findViewById(R.id.txtFechaOrden);
        TextView txtIdOrden = rowview.findViewById(R.id.txtIdOrden);
        TextView txtCantidadOrden = rowview.findViewById(R.id.txtCantidadOrden);
        TextView txtCostoOrden = rowview.findViewById(R.id.txtCostoOrden);

        txtFechaOrden.setText(pedidos.get(position).getFechaOrden());
        txtIdOrden.setText("Orden N-"+position+1);
        txtCantidadOrden.setText("Cantidad de medicamentos: "+pedidos.get(position).getTotalItems());
        txtCostoOrden.setText("Costo total: $"+String.format("%.2f",pedidos.get(position).getCosto()));



        return rowview;
    }
}
