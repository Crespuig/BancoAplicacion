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
import androidx.fragment.app.FragmentManager;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.adaptador.MovimientosCuentaAdapter;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.dialogos.DialogoListaMovimientos;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class Activity_Fragment_Movimiento extends Fragment{
    Activity_Fragment_Movimiento context = this;

    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;

    public Activity_Fragment_Movimiento(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_fragment_movimientos_cuenta, container, false);
        listaMovimientos = (ListView) view.findViewById(R.id.listaMovimientos);
        mostrarDetalle(cuenta);
        return view;
    }

    public void mostrarDetalle(Cuenta c){
        mbo = MiBancoOperacional.getInstance(context.getActivity());

        adaptador = new MovimientosCuentaAdapter<>(this, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);

        listaMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogoListaMovimientos dialogoListaMovimientos = new DialogoListaMovimientos();
                dialogoListaMovimientos.info = adapterView.getItemAtPosition(i).toString();
                dialogoListaMovimientos.show(fragmentManager, "tagListaMovimientos");
            }
        });
    }


}
