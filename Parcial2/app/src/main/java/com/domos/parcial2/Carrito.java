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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;


import com.domos.parcial2.datos.Item;
import com.domos.parcial2.datos.Orden;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Carrito extends AppCompatActivity {

    public static Orden estaOrden;

    ImageButton btnMenu, btnRegresar;
    Button btnHacerPedido;
    ListView itemsEnCarrito;
    TextView tvNumeroArticulos, tvSubtotal;

    public static AdaptadorCarrito adapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        mAuth = FirebaseAuth.getInstance();
        inicializarControles();
        calcularTotalItems();



        //Hacer un pedido
        btnHacerPedido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(estaOrden.getTotalItems()==0){
                    Toast.makeText(Carrito.this,"El carrito esta vacio.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm:ss z");
                String currentDateandTime = sdf.format(new Date());

                estaOrden.setFechaOrden(currentDateandTime);

                String usuarioActivo = mAuth.getUid();
                MainActivity.refClientes.child(usuarioActivo).child("ordenes").push().setValue(estaOrden);

                //Vaciar carrito
                MainActivity.listaItemsCarrito.clear();
                estaOrden = new Orden(0,0);
                calcularTotalItems();

                btnHacerPedido.setEnabled(false);
                Toast.makeText(Carrito.this,"Pedido en marcha :)", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();


        }});

        btnMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showPopup(v);
            }});

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, MainActivity.class);
                startActivity(intent);
            }
        });



        adapter = new AdaptadorCarrito(Carrito.this, MainActivity.listaItemsCarrito);
        itemsEnCarrito.setAdapter(adapter);


        LocalBroadcastManager.getInstance(this).registerReceiver(actualizarCarrito, new IntentFilter("actualizarCarrito"));


    }

    @Override
    protected void onResume() {
        super.onResume();
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

        if(MainActivity.listaItemsCarrito.isEmpty()){
            tvNumeroArticulos.setText("0 articulos");
            tvSubtotal.setText("$ 0.00");
            return;
        }
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
        btnMenu = findViewById(R.id.ibtnMenu_carrito);
        btnHacerPedido = findViewById(R.id.btnHacerPedidoCarrito);
        btnRegresar = findViewById(R.id.ibtnFlechaRegreso);

        tvNumeroArticulos=findViewById(R.id.tvwNumeroArticulos);
        tvSubtotal=findViewById(R.id.tvwSubtotalDolares);

    }

    public void carritoEstaVacio(){

        if (estaOrden.getTotalItems()==0) {
           btnHacerPedido.setEnabled(false);
        }else{
            btnHacerPedido.setEnabled(true);
        }
    }
    

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    //El primero lleva a mis ordenes
                    case R.id.menuitem1:
                        intent = new Intent(Carrito.this, Pedidos.class);
                        startActivity(intent);
                        return true;

                    //el segundo cierra sesion
                    case R.id.menuitem2:

                        FirebaseAuth.getInstance().signOut();
                        intent = new Intent(Carrito.this, InicioSesion.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }

            }
        });

        inflater.inflate(R.menu.menu_principal, popup.getMenu());
        popup.show();
    }



}