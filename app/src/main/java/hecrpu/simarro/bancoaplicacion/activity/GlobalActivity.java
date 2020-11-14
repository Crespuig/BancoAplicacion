package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import hecrpu.simarro.bancoaplicacion.adaptador.GlobalAdapter;
import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Global;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.fragment.GlobalListener;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalActivity extends AppCompatActivity /*implements AdapterView.OnItemClickListener, GlobalListener*/ {
    private ListView listaCuentas;
    private MiBancoOperacional mbo;
    private GlobalAdapter<Cuenta> adaptador;
    private Cliente cliente;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        Activity_Fragment_Global frgListaGlobal = (Activity_Fragment_Global) getSupportFragmentManager().findFragmentById(R.id.frgListaGlobal);
        //frgListaGlobal.setGlobalListener(this);

        /*mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        listaCuentas = (ListView) findViewById(R.id.listaGlobal);

        adaptador = new GlobalAdapter<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);*/

    }

    /*@Override
    public void onItemClick(AdapterView<?> parent , View view, int position, long id){
        Cuenta cuentaActual = (Cuenta) adaptador.getItem(position);
        String msg = "- Elegiste la cuenta:\n" + cuentaActual.getNumeroCuenta() + "\n- Con saldo" + cuentaActual.getSaldoActual();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(GlobalActivity.this, MovimientosCuentaActivity.class);
        intent.putExtra("cuenta", cuentaActual);
        startActivity(intent);
    }*/

    /*@Override
    public void onCuentaSeleccionada(Cuenta c) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.frgMovimiemtoCuenta) != null);

        if (hayDetalle) {
            ((Activity_Fragment_Movimiento) getSupportFragmentManager().findFragmentById(R.id.frgMovimiemtoCuenta)).mostrarDetalle(c);
        } else {
            Intent i = new Intent(this, MovimientosCuentaActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Cuenta", c);
            i.putExtras(bundle);
            startActivity(i);
        }
    }*/
}