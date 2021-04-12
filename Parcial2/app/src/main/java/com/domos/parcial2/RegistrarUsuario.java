package com.domos.parcial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarUsuario extends AppCompatActivity {

    TextInputEditText edtRegistrarCorreo, edtRegistrarContra;
    Button btnRegistrarCuenta;
    ImageButton ibtnRegresarInicioSesion;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        mAuth = FirebaseAuth.getInstance();
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
                String correo, contra;
                correo = edtRegistrarCorreo.getText().toString();
                contra = edtRegistrarContra.getText().toString();

                if(TextUtils.isEmpty(correo)){
                    Toast.makeText(getApplicationContext(), "Por favor ingrese un correo", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(contra)){
                    Toast.makeText(getApplicationContext(), "Por favor ingrese una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(correo, contra)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Registro de cuenta exitosa", Toast.LENGTH_LONG).show();

                                    //Esto sucede solo si el registro fue correcto
                                    Intent intent = new Intent(RegistrarUsuario.this, InicioSesion.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getApplicationContext(), "Registro de cuenta fallido", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


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