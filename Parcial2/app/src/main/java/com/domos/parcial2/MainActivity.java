package com.domos.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.domos.parcial2.datos.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.domos.parcial2.datos.Medicamento;

public class MainActivity extends AppCompatActivity {

    List<Medicamento> medicamentos;
    ListView listaMedicamentos;

    //Declarando lista de items para carrito ( global )
   ` public static List<Item> listaItemsCarrito;`

    ImageButton btnIrCarrito, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recuperarCarritoPreferencias();

        cargarMedicamentos();
        cargarListviewMedicamentos();

        btnIrCarrito = findViewById(R.id.ibtnCarrito);
        btnMenu = findViewById(R.id.ibtnMenu);



        btnIrCarrito.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InicioSesion.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void recuperarCarritoPreferencias(){


        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        // creating a variable for gson.
        Gson gson = new Gson();
        String json = sharedPreferences.getString("carrito", null);

        //mapeando string
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();

        listaItemsCarrito = gson.fromJson(json, type);

        //Si shared preferences esta vacio crear la lista

        if (listaItemsCarrito == null) {
            listaItemsCarrito = new ArrayList<>();
        }
    }


    private void cargarMedicamentos(){

        medicamentos = new ArrayList<>();

        String[][] datosMedicamentos = {
                {"1","Loratadina LS", "5.52", String.valueOf(R.drawable.loratadina_ls), "Loratadina LS 10MG x 10 tabletas", "Para el alivio de los síntomas asociados con rinitis, estornudos frecuentes, secreción nasal, picazón nasal y ojos. Casos de urticaria aguda o crónica y alergias cutáneas, respiratorias y picaduras de insectos."},
                {"2","Acetaminofen MK", "7.22", String.valueOf(R.drawable.acetaminofen), "Acetaminofen MK 500MG x 100 tabletas", "Alivia el dolor de cabeza, dolores provocados por catarro comun, gripe, vacunaciones, enfermedades virales, dolores de dientes, dolores de oidos y dolores de garganta."},
                {"3","Tradol Tramadol HCI","20.19",String.valueOf(R.drawable.tradol),"Tradol 50MG x 50 capsulas","Está indicado para el tratamiento del dolor moderado a severo; pero siempre que su uso sea por corto tiempo."},
                {"4","Ambroxol Suizos","5.74",String.valueOf(R.drawable.ambroxol),"Ambroxol Suizos 15MG/5ML solución frasco 120ml","Recomendado en el tratamiento de afecciones de las vías respiratorias con una formación patológica de secreciones tales como: bronquitis agudas y crónicas, neumonías, bronquiestasis, asma bronquial, sinusitis y caso de afecciones rinofaringeas."},
                {"5","Ibuprofeno MK","12.75",String.valueOf(R.drawable.ibuprofeno),"Ibuprofeno MK 600MG x 50 tabletas","Antiinflamatorio no esteroideo con propiedades analgésicas y antipiréticas."},
                {"6","Nerviflora","8.03",String.valueOf(R.drawable.nerviflora),"Nerviflora x 30 tabletas","Efecto calmante en nerviosismo, agitación, angustia y ansiedad. En casos de insomnio como inductor del sueño."},
                {"7","Trileptal","14.5",String.valueOf(R.drawable.trileptal),"Trileptal 60MG/ML suspensión 100ml","Epilepsia (excepto ausencias). Tratamiento de las crisis tónico clónicas generalizadas primarias y de las crisis parciales con o sin generalización secundaria. Tratamiento del dolor neuropático, incluyendo:neuralgia del trigémino. Neuropatía diabética. Síndrome doloroso regional. Dolor fantasma. Neuropatías por atrapamiento."},
                {"8","Aspirina infantil","10.8",String.valueOf(R.drawable.aspirina),"Aspirina infantil 100MG x 100 tabletas masticables","Indicado para el alivio sintomático del dolor de cabeza, dolor de muelas, dolor de garganta relacionado con resfrío, dolores musculares y de articulaciones, dolor de espalda, dolores menores provocados por la artritis. Alivio sintomático del dolor yla fiebre provocados por el resfrío común o influenza."},
                {"9","Geriasil H7","10.21",String.valueOf(R.drawable.geriasil),"Geriasil H7 x 30 capsulas (multivita+antioxid)","Indicado para adultos y recomendado en trastornos de la potencia sexual, jóvenes en estado de agotamiento mental y físico, disminución de la memoria, climaterio femenino y masculino, insomnio, irritabilidad, disminución de la capacidad visual y regula el sistema cardiovascular."},
                {"10","Clorfenil","4.39",String.valueOf(R.drawable.clorfenil),"Clorfenil jarabe frasco 120ml (Clorfeniramina)","Prevención y tratamiento de manifestaciones alérgicas estacionarias, urticarias, jaquecas y asma alérgica. Alivia el prurito no específico, rinitis vasomotora, eczema y dermatitis alérgica. Alergias alimentarias, neurodermatitis, y reacciones a las trasfusiones, al suero y medicamentos. Erupciones exantemáticas, tales como: sarampión, rubeola y varicela."}
        };

        for(int i = 0; i < datosMedicamentos.length; i++){

            String id = datosMedicamentos[i][0];
            String nombre = datosMedicamentos[i][1];
            double precio = Double.parseDouble(datosMedicamentos[i][2]);
            int foto = Integer.parseInt(datosMedicamentos[i][3]);
            String descripCorta = datosMedicamentos[i][4];
            String descripLarga = datosMedicamentos[i][5];

            Medicamento medicamento = new Medicamento(id,nombre,precio,descripCorta,descripLarga,foto);

            medicamentos.add(medicamento);
        }
    }

    private void cargarListviewMedicamentos() {

        listaMedicamentos = findViewById(R.id.listaMedicamentos);


        // Cuando el usuario haga clic en la lista (para editar registro)
        listaMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), DetalleMedicamento.class);
                //Esta linea me puede servir para la key del medicamento en firebase
                //intent.putExtra("key", personas.get(i).getKey());

                intent.putExtra("id",medicamentos.get(i).ID);
                intent.putExtra("nombre",medicamentos.get(i).getNombre());
                intent.putExtra("precio", String.valueOf(medicamentos.get(i).getPrecio()));
                intent.putExtra("descripcionCorta", medicamentos.get(i).getDescripcionCorta());
                intent.putExtra("descripcionLarga", medicamentos.get(i).getDescripcionLarga());
                intent.putExtra("foto", String.valueOf(medicamentos.get(i).getFoto()));

                startActivity(intent);
            }
        });

        AdaptadorMedicamento adapter = new AdaptadorMedicamento(MainActivity.this, medicamentos);
        listaMedicamentos.setAdapter(adapter);

    }
}