package hecrpu.simarro.bancoaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class CambiarClave extends AppCompatActivity {

    private EditText usuario;
    private EditText password;
    private Button btnCambiaPass;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        btnCambiaPass = (Button) findViewById(R.id.btnCambiaPass);
        dialog = new ProgressDialog(this);


        btnCambiaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CambiarClave.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CambiarClave.this, PrincipalActivity.class);
                dialog.setMessage("Espera");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                startActivityForResult(intent, 0);
                dialog.dismiss();
            }
        });
    }


}