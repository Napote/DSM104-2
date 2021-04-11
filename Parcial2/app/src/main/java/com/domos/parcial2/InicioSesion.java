package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class InicioSesion extends AppCompatActivity {

    TextInputEditText edtCorreo, edtContra;
    Button btnIniciarSesion, btnRegistrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        inicializar();
    }

    public void inicializar(){

        edtCorreo = (TextInputEditText) findViewById(R.id.edtIniciarCorreo);
        edtContra = (TextInputEditText) findViewById(R.id.edtIniciarContra);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrar);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Programar las funciones de firebase que revisan si el usuario existe

                //Esto solo debe suceder cuando el inicio de sesión es exitoso
                Intent intent = new Intent(InicioSesion.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioSesion.this,RegistrarUsuario.class);
                startActivity(intent);
            }
        });


    }
}