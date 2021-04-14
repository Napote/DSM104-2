package com.aj.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText nombreEmpleado1, nombreEmpleado2, nombreEmpleado3;
    EditText horasEmpleado1, horasEmpleado2, horasEmpleado3;
    Spinner cargoEmpleado1, cargoEmpleado2, cargoEmpleado3;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definiendo campos de formualrio
        nombreEmpleado1 = (EditText) findViewById(R.id.eTextNombre1);
        nombreEmpleado2 = (EditText) findViewById(R.id.eTextNombre2);
        nombreEmpleado3 = (EditText) findViewById(R.id.eTextNombre3);

        horasEmpleado1 = (EditText) findViewById(R.id.eTextHoras1);
        horasEmpleado2 = (EditText) findViewById(R.id.eTextHoras2);
        horasEmpleado3 = (EditText) findViewById(R.id.eTextHoras3);

        //Populando Spinners
        cargoEmpleado1= (Spinner) findViewById(R.id.spinner1);
        cargoEmpleado2= (Spinner) findViewById(R.id.spinner2);
        cargoEmpleado3= (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cargos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargoEmpleado1.setAdapter(adapter);
        cargoEmpleado2.setAdapter(adapter);
        cargoEmpleado3.setAdapter(adapter);


    }

    public void validarEntrada(View view){



        if(nombreEmpleado1.getText().toString().isEmpty() || horasEmpleado1.getText().toString().isEmpty()){
            Toast notificacion= Toast.makeText(this,"Para continuar, llene todos los campos del Empleado 1",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        if(nombreEmpleado2.getText().toString().isEmpty() || horasEmpleado2.getText().toString().isEmpty()){
            Toast notificacion= Toast.makeText(this,"Para continuar, llene todos los campos del Empleado 2",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        if(nombreEmpleado3.getText().toString().isEmpty() || horasEmpleado3.getText().toString().isEmpty()){
            Toast notificacion= Toast.makeText(this,"Para continuar, llene todos los campos del Empleado 3",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }


        Double fHorasEmpleado1 = Double.valueOf(horasEmpleado1.getText().toString());
        Double fHorasEmpleado2 = Double.valueOf(horasEmpleado2.getText().toString());
        Double fHorasEmpleado3 = Double.valueOf(horasEmpleado3.getText().toString());

        if(fHorasEmpleado1 <= 0.0 || fHorasEmpleado2 <=0.0 || fHorasEmpleado3 <= 0.0){

            Toast notificacion= Toast.makeText(this,"No se puede continuar, las horas del empleado deben ser mayores a 0",Toast.LENGTH_LONG);
            notificacion.show();
            return;
        }

        Empleado empleado1 = new Empleado(nombreEmpleado1.getText().toString(), fHorasEmpleado1, cargoEmpleado1.getSelectedItem().toString() );
        Empleado empleado2 = new Empleado(nombreEmpleado2.getText().toString(), fHorasEmpleado2, cargoEmpleado2.getSelectedItem().toString() );
        Empleado empleado3 = new Empleado(nombreEmpleado3.getText().toString(), fHorasEmpleado3, cargoEmpleado3.getSelectedItem().toString() );


       empleado1.setSueldoNeto(calcularSalarioNeto(empleado1.getHoras()));
       empleado2.setSueldoNeto(calcularSalarioNeto(empleado2.getHoras()));
       empleado3.setSueldoNeto(calcularSalarioNeto(empleado3.getHoras()));


       //calculando Bonos
        Boolean bonos = Boolean.FALSE;

        String combinacionCargos = empleado1.getCargo() + empleado2.getCargo() + empleado3.getCargo();

        if(combinacionCargos.equals("GerenteAsistenteSecretaria")) {
            empleado1.setBono(0.0);
            empleado2.setBono(0.0);
            empleado3.setBono(0.0);
        }else{
            empleado1.setBono(calcularPorcentajeBono(empleado1.getCargo()));
            empleado2.setBono(calcularPorcentajeBono(empleado2.getCargo()));
            empleado3.setBono(calcularPorcentajeBono(empleado3.getCargo()));
            bonos = Boolean.TRUE;
        }



        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("datosEmpleado1", empleado1);
        i.putExtra("datosEmpleado2", empleado2);
        i.putExtra("datosEmpleado3",empleado3);
        i.putExtra("hayBonos", bonos);
        startActivity(i);

    }



    public Double calcularPorcentajeBono(String cargo){

        if (cargo.equals("Gerente"))
            return 0.1;
        else if (cargo.equals("Asistente"))
            return 0.05;
        else
           return  0.02;

    }


    public Double calcularSalarioNeto(Double horas){

        Double salarioNeto;

        if(horas <=160){
            salarioNeto = horas*9.75;
        }else{
            horas = horas - 160;
            salarioNeto = 160 * 9.75 + horas*11.50;
        }

        return salarioNeto;
    }





}