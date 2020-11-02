package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listaCuentas;
    private MiBancoOperacional mbo;
    private GlobalAdapter<Cuenta> adaptador;
    private Cliente cliente;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        listaCuentas = (ListView) findViewById(R.id.listaGlobal);

        adaptador = new GlobalAdapter<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent , View view, int position, long id){
        Cuenta cuentaActual = (Cuenta) adaptador.getItem(position);
        String msg = "- Elegiste la cuenta:\n" + cuentaActual.getNumeroCuenta() + "\n- Con saldo" + cuentaActual.getSaldoActual();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(GlobalActivity.this, MovimientosCuentaActivity.class);
        intent.putExtra("cuenta", cuentaActual);
        startActivity(intent);
    }
}