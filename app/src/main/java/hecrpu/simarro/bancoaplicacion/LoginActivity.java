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

        usuario = (EditText)findViewById(R.id.passworAnterior);
        password = (EditText)findViewById(R.id.passwordNuevo);


        api = MiBancoOperacional.getInstance(this);

        btnAcceder = (Button)findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(this);

        usuario.setText("11111111A");
        password.setText("1234");

    }

    @Override
    public void onClick(View view) {
        Cliente c = new Cliente();
        c.setNif(usuario.getText().toString());

        c.setClaveSeguridad(password.getText().toString());
        c = api.login(c);
        Toast.makeText(LoginActivity.this, "Bienvenido/a " + c.getNombre(), Toast.LENGTH_SHORT).show();

        if (c == null) {
            Toast.makeText(LoginActivity.this, "Los datos no coinciden con ningún cliente", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            intent.putExtra("cliente", c);
            startActivity(intent);
        }
    }
}
