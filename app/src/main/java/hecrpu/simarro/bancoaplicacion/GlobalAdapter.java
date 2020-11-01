package hecrpu.simarro.bancoaplicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalAdapter<T> extends ArrayAdapter<T> {
    public GlobalAdapter(@NonNull Context context, List<T> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Guardar la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View existe
        if (convertView == null) {
            //Si no existe, entonces inflarlo con two_line_list_item
            listItemView = inflater.inflate(R.layout.image_list_item, parent, false);
            //listItemView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
        }

        //Obteniendo las referencias de los textViews
        TextView numeroCuenta = (TextView) listItemView.findViewById(R.id.numeroCuenta);
        TextView saldoActual = (TextView) listItemView.findViewById(R.id.saldoActual);
        ImageView comida = (ImageView) listItemView.findViewById(R.id.imagen);

        //Obteniendo la instancia del item posicion actual
        Cuenta item = (hecrpu.simarro.bancoaplicacion.pojo.Cuenta) getItem(position);

        numeroCuenta.setText(item.getNumeroCuenta());
        saldoActual.setText((int) item.getSaldoActual());

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
