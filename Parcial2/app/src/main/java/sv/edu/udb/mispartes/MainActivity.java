package sv.edu.udb.mispartes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.mispartes.datos.Medicamento;

public class MainActivity extends AppCompatActivity {

    List<Medicamento> medicamentos;
    ListView listaMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarMedicamentos();
        cargarListviewMedicamentos();
    }

    private void cargarMedicamentos(){

        medicamentos = new ArrayList<>();

        String[][] datosMedicamentos = {
                {"1","Loratadina LS", "5.52", String.valueOf(R.drawable.loratadina_ls), "Loratadina LS 10MG x 10 tabletas", "Para el alivio de los síntomas asociados con rinitis, estornudos frecuentes, secreción nasal, picazón nasal y ojos. Casos de urticaria aguda o crónica y alergias cutáneas, respiratorias y picaduras de insectos."},
                {"2","Acetaminofen MK", "7.22", String.valueOf(R.drawable.acetaminofen), "Acetaminofen MK 500MG x 100 tabletas", "Alivia el dolor de cabeza, dolores provocados por catarro comun, gripe, vacunaciones, enfermedades virales, dolores de dientes, dolores de oidos y dolores de garganta."}
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

        /*
        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(PersonasActivity.this);
                ad.setMessage("Está seguro de eliminar registro?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PersonasActivity.refPersonas
                                .child(personas.get(position).getKey()).removeValue();

                        Toast.makeText(PersonasActivity.this,
                                "Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(PersonasActivity.this,
                                "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });
        */

        AdaptadorMedicamento adapter = new AdaptadorMedicamento(MainActivity.this, medicamentos);
        listaMedicamentos.setAdapter(adapter);

        //Esto lo arreglo ya cuando tenga el firebase
        /*
        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                personas.removeAll(personas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Persona persona = dato.getValue(Persona.class);
                    persona.setKey(dato.getKey());
                    personas.add(persona);
                }

                AdaptadorPersona adapter = new AdaptadorPersona(PersonasActivity.this,
                        personas );
                listaPersonas.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
    }
}