package hecrpu.simarro.bancoaplicacion.adaptador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalAdapter<T> extends ArrayAdapter<T> {
    Activity context;
    ArrayList<Cuenta> cuentas;

    public GlobalAdapter(@NonNull Fragment context, List<T> cuentas) {
        super(context.getActivity(), R.layout.activity_global, cuentas);
        this.context = context.getActivity();
        this.cuentas = (ArrayList<Cuenta>) cuentas;
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
            listItemView = inflater.inflate(R.layout.image_list_global, parent, false);
            //listItemView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
        }

        //Obteniendo las referencias de los textViews
        TextView numeroCuenta = (TextView) listItemView.findViewById(R.id.numeroCuenta);
        TextView saldoActual = (TextView) listItemView.findViewById(R.id.saldoActual);

        //Obteniendo la instancia del item posicion actual
        Cuenta item = (hecrpu.simarro.bancoaplicacion.pojo.Cuenta) getItem(position);

        numeroCuenta.setText("Cuenta: " + item.getNumeroCuenta());
        saldoActual.setText("Saldo: " + Float.toString(item.getSaldoActual()) + "€");

        if (item.getSaldoActual()<0){
            saldoActual.setTextColor(Color.parseColor("#FFFF0009"));
        }else{
            saldoActual.setTextColor(Color.parseColor("#005004"));
        }

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
