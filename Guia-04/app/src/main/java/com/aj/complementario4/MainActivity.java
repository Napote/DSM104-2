package com.aj.complementario4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static final double DESC_ISSS = 0.02;
    static final double DESC_AFP = 0.03;
    static final double DESC_RENTA = 0.04;
    static final double VALOR_HORA = 8.5;
    EditText nombreEmpleado, horasTrabajadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreEmpleado = (EditText) findViewById(R.id.txtNombre);
        horasTrabajadas = (EditText) findViewById(R.id.txtHoras);
    }

    public void calcularDescuento(View view){

        if(nombreEmpleado.getText().toString().isEmpty() || horasTrabajadas.getText().toString().isEmpty()){
            Toast notificacion= Toast.makeText(this,"Debe llenar todos los campos antes de continuar",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        if(Double.parseDouble(horasTrabajadas.getText().toString()) == 0){
            Toast notificacion= Toast.makeText(this,"Las horas trabajadas deben ser mayor a 0",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }




        double salarioLiquido = Double.parseDouble(horasTrabajadas.getText().toString()) * VALOR_HORA;

        //calculando descuentos

        double descuentoISSS = salarioLiquido * DESC_ISSS;
        double descuentoAFP = salarioLiquido * DESC_AFP;
        double descuentoRenta = salarioLiquido * DESC_RENTA;

        double salarioNeto = salarioLiquido - descuentoAFP - descuentoISSS - descuentoRenta;

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra ("salarioneto", String.valueOf(salarioNeto) );
        i.putExtra ("descuentoISSS", String.valueOf(descuentoISSS) );
        i.putExtra ("descuentoAFP", String.valueOf(descuentoAFP) );
        i.putExtra ("descuentoRenta", String.valueOf(descuentoRenta) );


        startActivity(i);


    }



}