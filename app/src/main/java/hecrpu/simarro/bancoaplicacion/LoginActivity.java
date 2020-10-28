package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import hecrpu.simarro.bancoaplicacion.bd.MiBD;
import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class LoginActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    EditText usuario;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClickDashboard(View view) {
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);

        MiBancoOperacional api = MiBancoOperacional.getInstance(this.getApplicationContext());
        Cliente c = new Cliente();

        if (usuario.getText() == null || password.getText() == null) {
            Toast.makeText(LoginActivity.this, "Los campos njo pueden estar vacios", Toast.LENGTH_SHORT).show();
        } else {
            api.login(c);
        }

        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivityForResult(intent, 0);


    }
}


    /*@Override
    public void onClick(View view) {
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);

        MiBancoOperacional api = MiBancoOperacional.getInstance(this.getApplicationContext());
        Cliente c  = new Cliente();

        if (usuario.getText() == null || password.getText() ==null){
            Toast.makeText(LoginActivity.this, "Los campos njo pueden estar vacios", Toast.LENGTH_SHORT).show();
        }else {
            api.login(c);
        }

        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivityForResult(intent, 0);

    }*/