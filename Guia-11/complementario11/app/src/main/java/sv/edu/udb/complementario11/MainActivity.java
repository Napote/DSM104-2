package sv.edu.udb.complementario11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,etcodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etcodigo  = (EditText)findViewById(R.id.etcodigo);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);

    }


    public void alta(View v) {
        if(TextUtils.isEmpty(etcodigo.getText())
                || TextUtils.isEmpty(et1.getText())
                || TextUtils.isEmpty(et2.getText())
                || TextUtils.isEmpty(et3.getText())
                || TextUtils.isEmpty(et4.getText())

        ){
            Toast.makeText(this, "Asegurese de llenar todos los campos",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = etcodigo.getText().toString();
        String nombre = et1.getText().toString();
        String apellido = et2.getText().toString();
        String edad = et3.getText().toString();
        String telefono = et4.getText().toString();


        ContentValues registro = new ContentValues();

        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("edad", edad);
        registro.put("telefono", telefono);


        try {
            bd.insertOrThrow("personas", null, registro);
            bd.close();
            etcodigo.setText("");
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            Toast.makeText(this, "Se cargaron los datos de la persona",Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, "ERROR!! No se cargaron los datos de la persona" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void consultaporcodigo(View v) {

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");

        if(TextUtils.isEmpty(etcodigo.getText())){
            Toast.makeText(this, "Ingrese un codigo para realizar la busqueda",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = etcodigo.getText().toString();
        Cursor fila = bd.rawQuery("select nombre,apellido,edad,telefono from personas where codigo=" + cod, null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No existe una persona con ese codigo",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {



        if(TextUtils.isEmpty(etcodigo.getText())){
            Toast.makeText(this, "Ingrese un codigo para eliminar un registro",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= etcodigo.getText().toString();
        int cant = bd.delete("personas", "codigo=" + cod, null);
        bd.close();
        etcodigo.setText("");
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró la persona con el codigo "+cod,
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con ese código",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {



        if(TextUtils.isEmpty(etcodigo.getText())){
            Toast.makeText(this, "Ingrese un codigo para realizar la modificacion",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = etcodigo.getText().toString();
        String nombre = et1.getText().toString();
        String apellido = et2.getText().toString();
        String edad = et3.getText().toString();
        String telefono = et4.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("edad", edad);
        registro.put("telefono", telefono);

        int cant = bd.update("personas", registro, "codigo=" + codigo, null);
        bd.close();
        if (cant == 1){
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }

        else
            Toast.makeText(this, "No existe una persona con ese código",
                    Toast.LENGTH_SHORT).show();




    }



}