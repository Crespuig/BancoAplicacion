package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import hecrpu.simarro.bancoaplicacion.pojo.Cuenta;

public class GlobalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    GlobalAdapter<Cuenta> adaptador;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        lista = (ListView)findViewById(R.id.listaGlobal);

        //Creamos un array de Stirngs
        String[] data = {"Elem1", "Elem2", "Elem3", "Elem4"};
        adaptador = new GlobalAdapter<>(this);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent , View view, int position, long id){
        Cuenta cuentaActual = (Cuenta) adaptador.getItem(position);
        String msg = "- Elegiste la cuenta:\n" + cuentaActual.getNumeroCuenta() + "\n- Con saldo" + cuentaActual.getSaldoActual();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}