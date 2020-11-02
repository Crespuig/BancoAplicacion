package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class MovimientosCuentaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos_cuenta);

        mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listaMovimientos);

        adaptador = new MovimientosCuentaAdapter<>(this, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
        listaMovimientos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}