package com.domos.parcial2;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        ImageButton agregarUnidad = rowview.findViewById(R.id.ibtnAgregarUnidades);
        ImageButton quitarUnidad = rowview.findViewById(R.id.ibtnQuitarUnidades);

        txtNombreMedicamento.setText(itemsCarrito.get(position).getNombre());
        txtUnidades.setText(""+itemsCarrito.get(position).getUnidades());
        txtCosto.setText("$ "+String.format("%.2f", itemsCarrito.get(position).getCosto()));
        imgMedicamento.setImageResource(itemsCarrito.get(position).getFoto());


        agregarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int unidades = itemsCarrito.get(position).getUnidades();
                if(unidades < 5){
                    unidades=unidades+1;
                    itemsCarrito.get(position).setUnidades(unidades);
                    double costoUnidades = unidades * itemsCarrito.get(position).getCostoUnidad();
                    itemsCarrito.get(position).setCosto((float)costoUnidades);
                    Carrito.adapter.notifyDataSetChanged();
                }
            }
        });


        return rowview;
    }
}
