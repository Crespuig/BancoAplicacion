package hecrpu.simarro.bancoaplicacion.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hecrpu.simarro.bancoaplicacion.adaptador.MovimientosCuentaAdapter;
import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.fragment.AllFragment;
import hecrpu.simarro.bancoaplicacion.fragment.OneFragment;
import hecrpu.simarro.bancoaplicacion.fragment.TwoFragment;
import hecrpu.simarro.bancoaplicacion.fragment.ZeroFragment;
import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;
import hecrpu.simarro.bancoaplicacion.pojo.Movimiento;

public class MovimientosCuentaActivity extends AppCompatActivity{
    MovimientosCuentaActivity context = this;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private MovimientosCuentaAdapter<Movimiento> adaptador;
    private Cuenta cuenta;
    AllFragment allFragment;
    ZeroFragment zeroFragment;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    private BottomNavigationView navigationView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos_cuenta);

        Bundle bundle = getIntent().getExtras();
        Cuenta cuenta = (Cuenta) bundle.getSerializable("Cuenta");
        ((Activity_Fragment_Movimiento)getSupportFragmentManager().findFragmentById(R.id.frgMovimientoCuenta)).mostrarDetalle(cuenta);

        navigationView = (BottomNavigationView) findViewById(R.id.menuNavigation);
        navigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) context);

        allFragment = new AllFragment();
        zeroFragment = new ZeroFragment();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();

        /*mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listaMovimientos);

        adaptador = new MovimientosCuentaAdapter<>(this, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
        listaMovimientos.setOnItemClickListener(this);*/
    }

    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
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
        transaction.commit();

    }
}