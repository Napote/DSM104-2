package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrarUsuario extends AppCompatActivity {

    TextInputEditText edtRegistrarCorreo, edtRegistrarContra;
    Button btnRegistrarCuenta;
    ImageButton ibtnRegresarInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        inicializar();
    }

    public void inicializar(){

        edtRegistrarCorreo = (TextInputEditText) findViewById(R.id.edtRegistrarCorreo);
        edtRegistrarContra = (TextInputEditText) findViewById(R.id.edtRegistrarContra);
        btnRegistrarCuenta = (Button) findViewById(R.id.btnRegistrarCuenta);
        ibtnRegresarInicioSesion = (ImageButton) findViewById(R.id.ibtnRegresarInicioSesion);

        btnRegistrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Programar las funciones de firebase que creen la nueva cuenta

                //Esto sucede solo si el registro fue correcto
                Intent intent = new Intent(RegistrarUsuario.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ibtnRegresarInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarUsuario.this, InicioSesion.class);
                startActivity(intent);
            }
        });
    }
}