package sv.edu.udb.mispartes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sv.edu.udb.mispartes.datos.Medicamento;

public class AdaptadorMedicamento extends ArrayAdapter<Medicamento> {

    List<Medicamento> medicamentos;
    private Activity context;

    public AdaptadorMedicamento(@NonNull Activity context, @NonNull List<Medicamento> medicamentos){
        super(context, R.layout.medicamento_layout, medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.medicamento_layout,null);
        else rowview = view;

        TextView txtNombreM = rowview.findViewById(R.id.txtNombreM);
        TextView txtDescripcionCortaM = rowview.findViewById(R.id.txtDescripcionCortaM);
        TextView txtPrecioM = rowview.findViewById(R.id.txtPrecioM);
        ImageView imgMedicamento = rowview.findViewById(R.id.imgMedicamento);

        txtNombreM.setText(medicamentos.get(position).getNombre());
        txtDescripcionCortaM.setText( medicamentos.get(position).getDescripcionCorta());
        txtPrecioM.setText(""+medicamentos.get(position).getPrecio());
        imgMedicamento.setImageResource(medicamentos.get(position).getFoto());

        return rowview;
    }
}
