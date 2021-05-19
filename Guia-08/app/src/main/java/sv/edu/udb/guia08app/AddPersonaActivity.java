package sv.edu.udb.guia08app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import sv.edu.udb.guia08app.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText edtDUI, edtNombre, edtGenero, edtPeso, edtAltura, edtFechaNacimiento;
    String key="",nombre="",dui="",genero="",peso="",altura="",nacimiento="",accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);
        inicializar();

    }

    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDUI = findViewById(R.id.edtDUI);
        edtGenero = findViewById(R.id.edtGenero);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        edtFechaNacimiento = findViewById(R.id.edtFechaNacimiento);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        nombre = datos.getString("nombre");
        genero = datos.getString("genero");
        peso = datos.getString("peso");
        altura = datos.getString("altura");
        nacimiento = datos.getString("fechaN");
        accion = datos.getString("accion");
        edtDUI.setText(dui);
        edtNombre.setText(nombre);
        edtGenero.setText(genero);
        edtAltura.setText(altura);
        edtPeso.setText(peso);
        edtFechaNacimiento.setText(nacimiento);
    }

    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String dui = edtDUI.getText().toString();
        String genero = edtGenero.getText().toString();
        float peso = Float.parseFloat(edtPeso.getText().toString());
        float altura = Float.parseFloat(edtAltura.getText().toString());
        String nacimiento = edtFechaNacimiento.getText().toString();
        // Se forma objeto persona
        Persona persona = new Persona(dui,nombre,genero,peso,altura,nacimiento);

        Log.i("nombre", nombre);
        Log.i("dui",dui);
        Log.i("genero", genero);
        Log.i("peso", String.valueOf(peso));
        Log.i("altura", String.valueOf(altura));
        Log.i("nacimiento", nacimiento);

        Log.i("nombrePersona", persona.getNombre());
        Log.i("duiPersona",persona.getDui());
        Log.i("generoPersona", persona.getGenero());
        Log.i("pesoPersona", String.valueOf(persona.getPeso()));
        Log.i("alturaPersona", String.valueOf(persona.getAltura()));
        Log.i("nacimientoPersona", persona.getFechaNacimiento());


        if (accion.equals("a")) { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }
}