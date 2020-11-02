package hecrpu.simarro.bancoaplicacion;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class MovimientosCuentaAdapter<T> extends ArrayAdapter<T> {
    public MovimientosCuentaAdapter(@NonNull Context context, List<T> objects) {
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
            listItemView = inflater.inflate(R.layout.movimientos_list, parent, false);
            //listItemView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
        }

        //Obteniendo las referencias de los textViews
        TextView id = (TextView) listItemView.findViewById(R.id.id);
        TextView tipo = (TextView) listItemView.findViewById(R.id.tipo);
        TextView fechaOperacion = (TextView) listItemView.findViewById(R.id.fechaOperacion);
        TextView descripcion = (TextView) listItemView.findViewById(R.id.descripcion);
        TextView importe = (TextView) listItemView.findViewById(R.id.importe);
        TextView cuentaOrigen = (TextView) listItemView.findViewById(R.id.cuentaOrigen);
        TextView cuentaDestino = (TextView) listItemView.findViewById(R.id.cuentaOrigen);

        //Obteniendo la instancia del item posicion actual
        Movimiento item = (hecrpu.simarro.bancoaplicacion.pojo.Movimiento) getItem(position);

        id.setText("ID: " + item.getId());
        tipo.setText("Tipo: " + item.getTipo());
        fechaOperacion.setText((item.getFechaOperacion()).toString());
        descripcion.setText(item.getDescripcion());
        importe.setText(Float.toString(item.getImporte()) + "€");
        /*cuentaOrigen.setText(item.getCuentaOrigen().getNumeroCuenta().toString());
        cuentaDestino.setText(item.getCuentaDestino().toString());*/

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
