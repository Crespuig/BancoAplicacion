package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hecrpu.simarro.bancoaplicacion.adaptador.MovimientosCuentaAdapter;
import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class MovimientosCuentaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos_cuenta);

        Bundle bundle = getIntent().getExtras();
        Cuenta cuenta = (Cuenta) bundle.getSerializable("Cuenta");
        ((Activity_Fragment_Movimiento)getSupportFragmentManager().findFragmentById(R.id.frgMovimientoCuenta)).mostrarDetalle(cuenta);

        /*mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listaMovimientos);

        adaptador = new MovimientosCuentaAdapter<>(this, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
        listaMovimientos.setOnItemClickListener(this);*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}