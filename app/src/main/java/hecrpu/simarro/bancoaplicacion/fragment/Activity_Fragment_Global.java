package hecrpu.simarro.bancoaplicacion.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.fragment.app.Fragment;

import hecrpu.simarro.bancoaplicacion.PrincipalActivity;
import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.activity.MovimientosCuentaActivity;
import hecrpu.simarro.bancoaplicacion.adaptador.GlobalAdapter;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class Activity_Fragment_Global extends Fragment implements AdapterView.OnItemClickListener {
    Activity_Fragment_Global context = this;

    private GlobalListener listener;
    private ListView listaCuentas;
    private MiBancoOperacional mbo;
    private GlobalAdapter<Cuenta> adaptador;
    private Cliente cliente;
    Activity_Fragment_Movimiento fragment_movimiento;


    public Activity_Fragment_Global(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_fragment_global, container, false);

    }

    /*public Activity_Fragment_Global newInstance(Cliente cliente){
        Activity_Fragment_Global fragment = new Activity_Fragment_Global();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Cliente", cliente);
        fragment.setArguments(bundle);
        return fragment;
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mbo = MiBancoOperacional.getInstance(context.getActivity());

        listaCuentas = (ListView) getView().findViewById(R.id.listaGlobal);

        adaptador = new GlobalAdapter<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);


    }

    public void setGlobalListener(GlobalListener globalListener) {
        this.listener = globalListener;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cuenta c = adaptador.getItem(i);
        fragment_movimiento = new Activity_Fragment_Movimiento(c);
        ((PrincipalActivity) getActivity()).cambiaFragment(R.id.fragment, fragment_movimiento);
    }
}
