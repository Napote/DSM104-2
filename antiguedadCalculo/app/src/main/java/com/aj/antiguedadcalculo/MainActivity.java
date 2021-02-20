package com.aj.antiguedadcalculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText sueldoEmpleado, antiguedadEmpleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sueldoEmpleado = (EditText) findViewById(R.id.txtSueldo);
        antiguedadEmpleado = (EditText) findViewById(R.id.txtAntiguedad);
    }

    public void validarEntrada(View view){

        if(sueldoEmpleado.getText().toString().isEmpty() || antiguedadEmpleado.getText().toString().isEmpty()){
            Toast notificacion= Toast.makeText(this,"Error. llene los campos vacios",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        Float fSueldo = Float.parseFloat(sueldoEmpleado.getText().toString());
        Float fAntiguedad = Float.parseFloat(antiguedadEmpleado.getText().toString());
        double fSueldoPagar =0;

        if(fSueldo == 0 || fAntiguedad == 0){
            Toast notificacion= Toast.makeText(this,"Error. introduzca valores diferentes de 0",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        //No debe ser menor a $100
        if(fSueldo < 100){
            Toast notificacion= Toast.makeText(this,"Error. el salario no debe ser menor a $100",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        if(fSueldo < 500 && fAntiguedad >= 10){
            fSueldoPagar = 0.2*fSueldo + fSueldo;
        }

        if(fSueldo < 500 && fAntiguedad < 10){
            fSueldoPagar = 0.05*fSueldo + fSueldo;
        }

        if(fSueldo >= 500){
            fSueldoPagar = new Float(fSueldo);
        }

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("sueldoCalculado",String.valueOf( fSueldoPagar));
        startActivity(i);

    }
}