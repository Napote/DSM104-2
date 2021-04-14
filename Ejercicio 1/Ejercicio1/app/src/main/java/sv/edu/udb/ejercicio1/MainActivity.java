package sv.edu.udb.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtA, txtB, txtC;
    Toast mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        txtC = (EditText) findViewById(R.id.txtC);
    }

    public void resolver(View v){

        if(campoCompletado(txtA.getText().toString()) && campoCompletado(txtB.getText().toString()) && campoCompletado(txtC.getText().toString())){

            Intent i = new Intent(this, resultados.class);

            int valorA = Integer.parseInt(txtA.getText().toString());
            int valorB = Integer.parseInt(txtB.getText().toString());
            int valorC = Integer.parseInt(txtC.getText().toString());

            double discriminante = Math.sqrt(Math.pow(valorB,2) - (4*valorA*valorC));

            if(valorA != 0){
                if(discriminante == 0){
                    double x = (-valorB)/(2*valorA);
                    i.putExtra("x1", ""+x);
                    i.putExtra("x2","");
                    startActivity(i);
                }else if(discriminante > 0){
                    double x1 = (-valorB + discriminante)/(2*valorA);
                    double x2 = (-valorB - discriminante)/(2*valorA);
                    i.putExtra("x1", ""+x1);
                    i.putExtra("x2", ""+x2);
                    startActivity(i);
                }else{
                    mensaje = Toast.makeText(this,"Esta ecuación solo tiene soluciones imaginarias", Toast.LENGTH_LONG);
                    mensaje.show();
                }
            }else{
                mensaje = Toast.makeText(this, "No se puede resolver esta ecuación, porque cualquier valor entre 0 es indeterminado", Toast.LENGTH_LONG);
                mensaje.show();
            }
        }else{
            mensaje = Toast.makeText(this, "Todos los campos deben estar completados", Toast.LENGTH_LONG);
            mensaje.show();
        }
    }

    public void limpiarCampos(View v){
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
    }

    public boolean campoCompletado(String cadena){
        if(cadena.equals("") || cadena.equals(" ")){
            return false;
        }else{
            return true;
        }
    }

}