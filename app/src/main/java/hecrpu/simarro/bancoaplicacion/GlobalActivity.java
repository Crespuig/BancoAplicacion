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

import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Cuenta> listaCuentas = new ArrayList<>();
    TextView numeroCuenta, saldoActual;
    GlobalAdapter<Cuenta> adaptador;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        /*numeroCuenta = (TextView) findViewById(R.id.numeroCuenta);
        saldoActual = (TextView) findViewById(R.id.saldoActual);*/

        Bundle objetoEnviado = getIntent().getExtras();
        Cliente cliente = null;

        if (objetoEnviado != null){
            cliente = (Cliente) objetoEnviado.getSerializable("cliente");
            cliente.setListaCuentas(listaCuentas);
        } else{
            Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent , View view, int position, long id){
        Cuenta cuentaActual = (Cuenta) adaptador.getItem(position);
        String msg = "- Elegiste la cuenta:\n" + cuentaActual.getNumeroCuenta() + "\n- Con saldo" + cuentaActual.getSaldoActual();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}