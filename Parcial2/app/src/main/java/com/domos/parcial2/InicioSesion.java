package com.domos.parcial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    TextInputEditText edtCorreo, edtContra;
    Button btnIniciarSesion, btnRegistrarse;
    String correo, contra;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        mAuth = FirebaseAuth.getInstance();
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

                correo = edtCorreo.getText().toString();
                contra = edtContra.getText().toString();

                if(TextUtils.isEmpty(correo)){
                    Toast.makeText(getApplicationContext(), "Por favor ingrese un correo", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(contra)){
                    Toast.makeText(getApplicationContext(), "Por favor ingrese una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(correo,contra)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();

                                    //Esto solo debe suceder cuando el inicio de sesión es exitoso
                                    Intent intent = new Intent(InicioSesion.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Inicio de sesión fallido", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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