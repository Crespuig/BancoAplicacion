package hecrpu.simarro.bancoaplicacion.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.adaptador.MovimientosCuentaAdapter;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class Activity_Fragment_Movimiento extends Fragment implements AdapterView.OnItemClickListener {
    Context context;

    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_movimientos_cuenta, container, false);

    }

    public static Activity_Fragment_Movimiento newInstance(Cuenta cuenta){
        Activity_Fragment_Movimiento fragment = new Activity_Fragment_Movimiento();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cuenta", cuenta);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("WrongViewCast")
    public void mostrarDetalle(Cuenta c){
        mbo = MiBancoOperacional.getInstance(context);

        if (getArguments() != null){
            cuenta = (Cuenta) getArguments().getSerializable("cuenta");
        }
        listaMovimientos = (ListView) getView().findViewById(R.id.listaMovimientos);

        adaptador = new MovimientosCuentaAdapter<>(this, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
        listaMovimientos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
