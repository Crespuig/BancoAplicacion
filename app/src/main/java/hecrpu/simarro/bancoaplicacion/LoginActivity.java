package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usuario;
    EditText password;
    MiBancoOperacional api;
    Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);


        api = MiBancoOperacional.getInstance(this.getApplicationContext());

        btnAcceder = (Button)findViewById(R.id.button2);
        btnAcceder.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Cliente c = new Cliente();
        c.setNif(usuario.getText().toString());
        Toast.makeText(LoginActivity.this, usuario.getText().toString(), Toast.LENGTH_SHORT).show();

        c.setClaveSeguridad(password.getText().toString());
        if (api.login(c) == null) {
            Toast.makeText(LoginActivity.this, "Los datos no coinciden con ningún cliente", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    }
}
