package sv.edu.udb.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultados extends AppCompatActivity {

    TextView txtResultado1, txtResultado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        txtResultado1 = (TextView) findViewById(R.id.txtResultado1);
        txtResultado2 = (TextView) findViewById(R.id.txtResultado2);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        String resultado1 = bundle.getString("x1");
        String resultado2 = bundle.getString("x2");

        if(resultado2.equals("") || resultado2.equals(" ")){
            txtResultado1.setText("Esta ecuación solo tiene una solución");
            txtResultado2.setText("X: "+resultado1);
        }else {
            txtResultado1.setText("X1: "+resultado1);
            txtResultado2.setText("X2: "+resultado2);
        }
    }

    public void finalizar(View v){
        finish();
    }
}