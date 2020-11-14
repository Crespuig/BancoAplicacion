package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hecrpu.simarro.bancoaplicacion.activity.GlobalActivity;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Global;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity {
    Cliente cliente;
    Activity_Fragment_Global fragment_global;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        constraintLayout = (ConstraintLayout) this.findViewById(R.id.principalLayout);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        fragment_global = new Activity_Fragment_Global(cliente);

    }

    public void onClickBtnSalir(View view) {
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickCambiarClave(View view) {
        Intent intent = new Intent(PrincipalActivity.this, CambiarClave.class);
        startActivityForResult(intent, 0);
        intent.putExtra("cliente", cliente);
        startActivityForResult(intent, 0);
    }

    public void onClickTransferencia(View view) {
        Intent intent = new Intent(PrincipalActivity.this, TransferActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickGlobal(View view) {

        cambiaFragment(R.id.fragment, fragment_global);
        constraintLayout.setVisibility(View.GONE);
        /*
        Intent intent = new Intent(PrincipalActivity.this, GlobalActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("cliente", cliente);
        intent.putExtra("cliente", cliente);
        startActivityForResult(intent, 0);*/
    }

    public void cambiaFragment(int id, Fragment fragment){
        //Sustituye un fragment por otro
        getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                //.addToBackStack(fragment.getTag())
                .commit();
    }
}