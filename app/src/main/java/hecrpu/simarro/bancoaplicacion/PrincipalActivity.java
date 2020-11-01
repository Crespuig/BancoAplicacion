package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void onClickBtnSalir(View view) {
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickCambiarClave(View view) {
        Intent intent = new Intent(PrincipalActivity.this, CambiarClave.class);
        startActivityForResult(intent, 0);
    }

    public void onClickTransferencia(View view) {
        Intent intent = new Intent(PrincipalActivity.this, TransferActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickGlobal(View view) {
        Cliente cliente = new Cliente();

        Intent intent = new Intent(PrincipalActivity.this, GlobalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }
}