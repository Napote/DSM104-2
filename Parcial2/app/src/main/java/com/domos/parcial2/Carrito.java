package com.domos.parcial2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Carrito extends AppCompatActivity {

    ImageButton btnRegresarPantallaPrincipal;
    Button btnHacerPedido;
    ListView itemsEnCarrito;
    static AdaptadorCarrito adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        itemsEnCarrito = findViewById(R.id.listaItemsCarrito);
        btnRegresarPantallaPrincipal = findViewById(R.id.ibtnFlechaRegreso);
        btnHacerPedido = findViewById(R.id.btnHacerPedidoCarrito);
        btnRegresarPantallaPrincipal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this,MainActivity.class);
                startActivity(intent);
            }
        });

       adapter = new AdaptadorCarrito(Carrito.this, MainActivity.listaItemsCarrito);
       itemsEnCarrito.setAdapter(adapter);

        if (MainActivity.listaItemsCarrito == null) {
            btnHacerPedido.setEnabled(false);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (MainActivity.listaItemsCarrito == null) {
            btnHacerPedido.setEnabled(false);
        }else{
            btnHacerPedido.setEnabled(true);
        }
    }




}