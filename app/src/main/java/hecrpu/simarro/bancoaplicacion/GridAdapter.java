package hecrpu.simarro.bancoaplicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter<T> extends ArrayAdapter<T> {

    private int layout;
    public GridAdapter(Context context, List<T> objects, @LayoutRes int layout) {
        super(context, 0, objects);
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView = convertView;

        if (null == convertView) {
            gridView = inflater.inflate(layout, parent, false);
        }

        TextView nom = (TextView) gridView.findViewById(R.id.txtNombre);
        TextView numCuenta = (TextView) gridView.findViewById(R.id.txtNumCuenta);
        TextView saldo = (TextView) gridView.findViewById(R.id.txtSaldo);

        Cuenta item = (Cuenta) getItem(position);

        nom.setText(item.getNombre());
        numCuenta.setText(item.getNumCuenta());
        saldo.setText(String.valueOf(item.getSaldo()) + "€");

        return gridView;
    }

}
