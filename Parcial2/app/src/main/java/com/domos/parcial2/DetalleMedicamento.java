package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMedicamento extends AppCompatActivity {

    TextView txtNombre, txtDescripCorta, txtDescripLarga, txtPrecio;
    ImageView imgMedicamento;
    String id, nombre, precio, descripCorta, descripLarga;
    int foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medicamento);

        cargarDatos();
    }

    public void cargarDatos(){
        txtNombre = (TextView) findViewById(R.id.txtNombreD);
        txtDescripCorta = (TextView) findViewById(R.id.txtDescripcionCortaD);
        txtPrecio = (TextView) findViewById(R.id.txtPrecioD);
        txtDescripLarga = (TextView) findViewById(R.id.txtDescripcionLargaD);
        imgMedicamento = (ImageView) findViewById(R.id.imgDetalle);

        Bundle medicamento = getIntent().getExtras();
        id = medicamento.getString("id");
        nombre = medicamento.getString("nombre");
        precio = medicamento.getString("precio");
        descripCorta = medicamento.getString("descripcionCorta");
        descripLarga = medicamento.getString("descripcionLarga");
        foto = Integer.parseInt(medicamento.getString("foto"));

        txtNombre.setText(nombre);
        txtDescripCorta.setText(descripCorta);
        txtPrecio.setText(precio);
        txtDescripLarga.setText(descripLarga);
        imgMedicamento.setImageResource(foto);

        txtDescripLarga.setMovementMethod(new ScrollingMovementMethod());
    }

    public void regresarDetallePrincipal(View view){
        Intent intent = new Intent(DetalleMedicamento.this, MainActivity.class);
        startActivity(intent);
    }
}