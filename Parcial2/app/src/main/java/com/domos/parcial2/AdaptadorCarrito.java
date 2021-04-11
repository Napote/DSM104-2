package com.domos.parcial2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        ImageView imgMedicamento = rowview.findViewById(R.id.imgMedicamentoCarrito);

        txtNombreMedicamento.setText(itemsCarrito.get(position).getNombre());
        txtUnidades.setText(""+itemsCarrito.get(position).getUnidades());
        txtCosto.setText("$ "+itemsCarrito.get(position).getCosto());
        imgMedicamento.setImageResource(itemsCarrito.get(position).getFoto());

        return rowview;
    }
}
