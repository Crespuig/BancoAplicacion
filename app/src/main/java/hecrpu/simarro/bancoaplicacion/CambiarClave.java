package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class CambiarClave extends AppCompatActivity {

    Cliente clienteActual;
    private EditText passwrodAnterior;
    private EditText passwordNuevo;
    private Button btnCambiaPass;
    MiBancoOperacional api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        clienteActual = (Cliente)getIntent().getSerializableExtra("cliente");

        passwrodAnterior = (EditText) findViewById(R.id.passworAnterior);
        passwordNuevo = (EditText) findViewById(R.id.passwordNuevo);
        btnCambiaPass = (Button) findViewById(R.id.btnCambiaPass);
        api = MiBancoOperacional.getInstance(this);

        btnCambiaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwrodAnterior.getText() != null || passwordNuevo.getText() != null){
                    if (clienteActual.getClaveSeguridad().contentEquals(passwrodAnterior.getText())){
                        //passwordNuevo.setVisibility(View.VISIBLE);
                        clienteActual.setClaveSeguridad(passwordNuevo.getText().toString());

                        Intent intent = new Intent(CambiarClave.this, PrincipalActivity.class);
                        intent.putExtra("cliente", clienteActual);

                        Toast.makeText(CambiarClave.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(CambiarClave.this, "La contraseña es incorrecta, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(CambiarClave.this, "Los capmpos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}