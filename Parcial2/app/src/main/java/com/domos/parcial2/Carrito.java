package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Carrito extends AppCompatActivity {

    ImageButton btnRegresarPantallaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        btnRegresarPantallaPrincipal = findViewById(R.id.ibtnFlechaRegreso);

        btnRegresarPantallaPrincipal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this,MainActivity.class);
                startActivity(intent);
            }
        });








    }








}