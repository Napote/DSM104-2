package com.aj.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    TextView txtEmpleadoMayorSueldo, txtEmpleadoMenorSueldo, txtEmpleadoSueldoMayor300;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        Empleado empleadosArray[] = new Empleado[3];
        empleadosArray[0] = (Empleado) intent.getSerializableExtra("datosEmpleado1");
        empleadosArray[1] = (Empleado) intent.getSerializableExtra("datosEmpleado2");
        empleadosArray[2] = (Empleado) intent.getSerializableExtra("datosEmpleado3");




        TextView nombreEmpleado1 = (TextView) findViewById(R.id.textNombreEmpleado1);
        nombreEmpleado1.setText( empleadosArray[0].getNombre());
        TextView cargoEmpleado1 = (TextView) findViewById(R.id.textCargoEmpleado1);
        cargoEmpleado1.setText( empleadosArray[0].getCargo());
        TextView descuentosEmpleado1 = (TextView) findViewById(R.id.textDescuentosEmpleado1);
        descuentosEmpleado1.setText( empleadosArray[0].getDescuentos());
        TextView sueldoEmpleado1 = (TextView) findViewById(R.id.textLiquidoEmpleado1);
        sueldoEmpleado1.setText( empleadosArray[0].getSueldoLiquido().toString());

        TextView nombreEmpleado2 = (TextView) findViewById(R.id.textNombreEmpleado2);
        nombreEmpleado2.setText(empleadosArray[1] .getNombre());
        TextView cargoEmpleado2 = (TextView) findViewById(R.id.textCargoEmpleado2);
        cargoEmpleado2.setText(empleadosArray[1] .getCargo());
        TextView descuentosEmpleado2 = (TextView) findViewById(R.id.textDescuentosEmpleado2);
        descuentosEmpleado2.setText(empleadosArray[1] .getDescuentos());
        TextView sueldoEmpleado2 = (TextView) findViewById(R.id.textLiquidoEmpleado2);
        sueldoEmpleado2.setText(empleadosArray[1].getSueldoLiquido().toString());

        TextView nombreEmpleado3 = (TextView) findViewById(R.id.textNombreEmpleado3);
        nombreEmpleado3.setText(  empleadosArray[2].getNombre());
        TextView cargoEmpleado3 = (TextView) findViewById(R.id.textCargoEmpleado3);
        cargoEmpleado3.setText(  empleadosArray[2].getCargo());
        TextView descuentosEmpleado3 = (TextView) findViewById(R.id.textDescuentosEmpleado3);
        descuentosEmpleado3.setText(  empleadosArray[2].getDescuentos());
        TextView sueldoEmpleado3 = (TextView) findViewById(R.id.textLiquidoEmpleado3);
        sueldoEmpleado3.setText(  empleadosArray[2].getSueldoLiquido().toString());



        int indiceEmpleadoMayorSueldo = 0;
        int indiceEmpleadoMenorSueldo = 0;

        String empleadosSueldoMayor300 = "";

        for(int i=1;i< 3;i++){
            if(empleadosArray[i].getSueldoLiquido() > empleadosArray[indiceEmpleadoMayorSueldo].getSueldoLiquido()){
                indiceEmpleadoMayorSueldo = i;
            }
            else if(empleadosArray[i].getSueldoLiquido() < empleadosArray[indiceEmpleadoMenorSueldo].getSueldoLiquido()){
                indiceEmpleadoMenorSueldo = i;
            }

            if (empleadosArray[i].getSueldoLiquido() > 300.0)
                empleadosSueldoMayor300 = empleadosSueldoMayor300 + " " + empleadosArray[i].getNombre() + ",";
        }




        txtEmpleadoMayorSueldo = (TextView) findViewById(R.id.textEmpleadoMayorSalario);
        txtEmpleadoMayorSueldo.setText(empleadosArray[indiceEmpleadoMayorSueldo].getNombre());

        txtEmpleadoMenorSueldo = (TextView) findViewById(R.id.textEmpleadoMenorSalario);
        txtEmpleadoMenorSueldo.setText(empleadosArray[indiceEmpleadoMenorSueldo].getNombre());


        txtEmpleadoSueldoMayor300 = (TextView) findViewById(R.id.textEmpleadosSalarioMayor300);
        txtEmpleadoSueldoMayor300.setText(empleadosSueldoMayor300);


        Boolean bonos = intent.getExtras().getBoolean("hayBonos");



        if(bonos == Boolean.FALSE){
            Toast notificacion= Toast.makeText(this,"No se aplicaron bonos",Toast.LENGTH_LONG);
            notificacion.show();
        }


    }
}