package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.domos.parcial2.datos.Medicamento;
import com.domos.parcial2.datos.Orden;
import com.domos.parcial2.datos.Pedido;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pedidos extends AppCompatActivity {

    List<Pedido> pedidos;
    ListView listaOrdenes;
    private FirebaseAuth mAuth;
    String usuarioActivo;
    Query ramaUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        mAuth = FirebaseAuth.getInstance();
        usuarioActivo = mAuth.getUid();
        ramaUsuario = MainActivity.refClientes.child(usuarioActivo).child("ordenes");

        cargarListviewPedidos();

        Log.i("DIOSITO",ramaUsuario.toString());
    }

    public void cargarListviewPedidos(){
        listaOrdenes = (ListView) findViewById(R.id.listaPedidos);

        //cargar los datos del firebase
        pedidos = new ArrayList<>();



        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        ramaUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pedidos.removeAll(pedidos);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Pedido pedido = dato.getValue(Pedido.class);
                    pedido.setID(dato.getKey());
                    pedidos.add(pedido);
                }

                AdaptadorPedido adapter = new AdaptadorPedido(Pedidos.this, pedidos);
                listaOrdenes.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void regresarPedidosPrincipal(View view){
        Intent intent = new Intent(Pedidos.this, MainActivity.class);
        startActivity(intent);
    }
}