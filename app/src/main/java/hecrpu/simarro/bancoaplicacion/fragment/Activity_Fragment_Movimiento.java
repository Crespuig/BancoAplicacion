package hecrpu.simarro.bancoaplicacion.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.adaptador.MovimientosCuentaAdapter;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.dao.MovimientoDAO;
import hecrpu.simarro.bancoaplicacion.dialogos.DialogoListaMovimientos;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class Activity_Fragment_Movimiento extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{
    Activity_Fragment_Movimiento context = this;

    private BottomNavigationView navigationView;
    private MovimientoDAO movimientoDAO;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;
    AllFragment allFragment;
    ZeroFragment zeroFragment;
    OneFragment oneFragment;
    TwoFragment twoFragment;

    public Activity_Fragment_Movimiento(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all, container, false);
        listaMovimientos = (ListView) view.findViewById(R.id.listaMovimientos);
        mostrarDetalle(cuenta);

        allFragment = new AllFragment();
        zeroFragment = new ZeroFragment();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();

        navigationView = (BottomNavigationView) view.findViewById(R.id.menuNavigation);
        navigationView.setOnNavigationItemSelectedListener(context);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, allFragment).commit();

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = null;
        switch (item.getItemId()){
            case R.id.navigationAll:
                transaction.replace(R.id.contenedor, allFragment);
                break;
            case R.id.navigationZero:
                transaction.replace(R.id.contenedor, zeroFragment);
                break;
            case R.id.navigationOne:
                transaction.replace(R.id.contenedor, oneFragment);
                break;
            case R.id.navigationTwo:
                transaction.replace(R.id.contenedor, twoFragment);
                break;
        }
        if (transaction != null){
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, allFragment)
                    .commit();
        }

        return true;
    }


}
