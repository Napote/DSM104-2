package com.aj.ejemplo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int valorActual; // valor del lbl
    TextView numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        numero = (TextView) findViewById(R.id.incrementable_lbl);

        if (savedInstanceState != null){
            valorActual = savedInstanceState.getInt("MyInt");
            numero.setText(String.valueOf(valorActual));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        super.onSaveInstanceState(outState);
        outState.putInt("MyInt", valorActual);
    }


    public void incrementar(View v){

        numero = (TextView) findViewById(R.id.incrementable_lbl);
        Button btn = (Button) findViewById(R.id.aumentar_btn);


        //conversion a int
        valorActual = Integer.parseInt(numero.getText().toString());

        if(valorActual==9)
            numero.setText("0");
        else{
            valorActual++;
            numero.setText(String.valueOf(valorActual));
        }
    } 

}