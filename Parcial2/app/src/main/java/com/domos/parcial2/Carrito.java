package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;


import com.domos.parcial2.datos.Item;
import com.domos.parcial2.datos.Orden;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Carrito extends AppCompatActivity {

    public static Orden estaOrden;

    ImageButton btnRegresarPantallaPrincipal;
    Button btnHacerPedido;
    ListView itemsEnCarrito;
    TextView tvNumeroArticulos, tvSubtotal;

    static AdaptadorCarrito adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        inicializarControles();
        calcularTotalItems();

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

        LocalBroadcastManager.getInstance(this).registerReceiver(actualizarCarrito, new IntentFilter("actualizarCarrito"));


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


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.listaItemsCarrito);
        editor.putString("carrito", json);
        editor.apply();


    }


    public void calcularTotalItems(){

        int numeroArticulos = 0;
        double sumaCostoArticulos = 0;

        for(Item i:MainActivity.listaItemsCarrito){
            numeroArticulos= numeroArticulos+ i.getUnidades();
            sumaCostoArticulos+=i.getCosto();
        }

        tvNumeroArticulos.setText(numeroArticulos+" articulos");
        tvSubtotal.setText("$ "+String.format("%.2f", sumaCostoArticulos));

        estaOrden=new Orden(sumaCostoArticulos,numeroArticulos);
    }

    //Declaración e inicialización del broadcastReceiver
    BroadcastReceiver actualizarCarrito = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int numeroArticulos = 0;
            double sumaCostoArticulos = 0;

            for(Item i:MainActivity.listaItemsCarrito){
                numeroArticulos= numeroArticulos+ i.getUnidades();
                sumaCostoArticulos+=i.getCosto();
            }

            tvNumeroArticulos.setText(numeroArticulos+" articulos");
            tvSubtotal.setText("$ "+String.format("%.2f", sumaCostoArticulos));

            //estaOrden=new Orden(sumaCostoArticulos,numeroArticulos);
            estaOrden.setCosto(sumaCostoArticulos);
            estaOrden.setTotalItems(numeroArticulos);
        }
    };


    public void inicializarControles(){
        itemsEnCarrito = findViewById(R.id.listaItemsCarrito);
        btnRegresarPantallaPrincipal = findViewById(R.id.ibtnFlechaRegreso);
        btnHacerPedido = findViewById(R.id.btnHacerPedidoCarrito);

        tvNumeroArticulos=findViewById(R.id.tvwNumeroArticulos);
        tvSubtotal=findViewById(R.id.tvwSubtotalDolares);

    }





}