package sv.edu.udb.guia12app.content.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    EditText edtCarnet, edtNombre, edtApellido;
    Button btnCancelar, btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        inicializar();
    }

    private void  inicializar(){

        edtCarnet = (EditText) findViewById(R.id.edtCarnet);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);

        btnAgregar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edtCarnet.getText().toString()) || !TextUtils.isEmpty(edtNombre.getText().toString()) || !TextUtils.isEmpty(edtApellido.getText().toString())){
                    //Aqui guardo la información en la bd
                    ContentValues datosAlumno = new ContentValues();
                    datosAlumno.put("nombre", edtNombre.getText().toString());
                    datosAlumno.put("apellido", edtApellido.getText().toString());
                    datosAlumno.put("carnet", edtCarnet.getText().toString());
                    getContentResolver().insert(StudentsContract.CONTENT_URI,datosAlumno);
                    Intent intent = new Intent(Formulario.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Formulario.this, "Error: No deje ningun campo vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Formulario.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}