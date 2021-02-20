package com.aj.antiguedadcalculo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText sueldoCalculado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b = getIntent().getExtras();

        sueldoCalculado = (EditText) findViewById(R.id.txtMensaje);
        sueldoCalculado.setText("El sueldo del empleado es \n$" +b.getString("sueldoCalculado"));

    }
}