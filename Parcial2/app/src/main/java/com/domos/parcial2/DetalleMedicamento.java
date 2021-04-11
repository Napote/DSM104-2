package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domos.parcial2.datos.Item;
import com.google.gson.Gson;

public class DetalleMedicamento extends AppCompatActivity {

    TextView txtNombre, txtDescripCorta, txtDescripLarga, txtPrecio;
    ImageView imgMedicamento;
    String id, nombre, precio, descripCorta, descripLarga;
    Button btnAgregarAlCarrito;
    int foto;

    Item enviarCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medicamento);

        cargarDatos();

        btnAgregarAlCarrito = findViewById(R.id.btnAgregarCarrito);

        btnAgregarAlCarrito.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Creando objeto
                enviarCarrito=new Item(id,nombre,Double.parseDouble(precio),1,foto);

                //antes de agregar el medicamento a la lista de elementos, tengo que verificar si ya existe ese medicamento

                Boolean existe = false;
                for(Item item : MainActivity.listaItemsCarrito){
                    if(item.ID.equals(enviarCarrito.ID)){
                        existe = true;
                        int unidades = item.getUnidades() + 1;
                        if(unidades <= 5){
                            item.setUnidades(unidades);
                            Log.i("SE INCREMENTA ELEMENTO", "SE INCREMENTÓ");
                            Toast.makeText(DetalleMedicamento.this, "Se ha incrementado la cantidad de unidades para este medicamento.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DetalleMedicamento.this,"Se ha alcanzado la cantidad máxima de 5 unidades para este medicamento", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                if(existe == false){
                    Log.i("SE CREA ELEMENTO","SE CREÓ");
                    MainActivity.listaItemsCarrito.add(enviarCarrito);
                    Toast.makeText(DetalleMedicamento.this, "Se ha agregado el item al carrito.", Toast.LENGTH_SHORT).show();
                }


                // method for saving the data in array list.
                // creating a variable for storing data in
                // shared preferences.
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

                // creating a variable for editor to
                // store data in shared preferences.
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // creating a new variable for gson.
                Gson gson = new Gson();

                // getting data from gson and storing it in a string.
                String json = gson.toJson(MainActivity.listaItemsCarrito);

                // below line is to save data in shared
                // prefs in the form of string.
                editor.putString("carrito", json);

                // below line is to apply changes
                // and save data in shared prefs.
                editor.apply();

                // after saving data we are displaying a toast message.
                //Toast.makeText(DetalleMedicamento.this, "Se ha agregado el item al carrito.", Toast.LENGTH_SHORT).show();

            }
        });




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