package com.aj.complementario4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView salarioNeto, descuentoAFP, descuentoRenta, descuentoSeguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        salarioNeto = (TextView)findViewById(R.id.txtSalarioNeto);
        descuentoAFP = (TextView)findViewById(R.id.txtDescuentoAFP);
        descuentoRenta = (TextView)findViewById(R.id.txtRenta);
        descuentoSeguro = (TextView)findViewById(R.id.txtSeguro);


        Bundle bundle = getIntent().getExtras();

        salarioNeto.setText("$" +bundle.getString("salarioneto"));

        descuentoAFP.setText("- $" +bundle.getString("descuentoAFP"));
        descuentoRenta.setText("- $" +bundle.getString("descuentoRenta"));
        descuentoSeguro.setText("- $" +bundle.getString("descuentoISSS"));

    }
}