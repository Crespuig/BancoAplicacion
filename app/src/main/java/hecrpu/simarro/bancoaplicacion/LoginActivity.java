package hecrpu.simarro.bancoaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


import hecrpu.simarro.bancoaplicacion.bd.MiBancoOperacional;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usuario;
    EditText password;
    MiBancoOperacional api;
    Button btnAcceder;
    SoundPool soundPool;
    int sonido_de_reproduccion;
    private Switch switchRemember;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.passworAnterior);
        password = (EditText) findViewById(R.id.passwordNuevo);


        api = MiBancoOperacional.getInstance(this);

        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(this);

        usuario.setText("11111111A");
        password.setText("1234");

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_de_reproduccion = soundPool.load(this, R.raw.sound_short, 1);

        prefs = getSharedPreferences("preferenciasbancarias", Context.MODE_PRIVATE);

    }

    public void audioMediaPlayer(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound_long);
        mediaPlayer.start();
    }

    private void saveOnPreferences(String usuario, String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        //audioMediaPlayer(view);
        Cliente c = new Cliente();
        c.setNif(usuario.getText().toString());

        c.setClaveSeguridad(password.getText().toString());
        c = api.login(c);
        c.setListaCuentas(api.getCuentas(c));
        Toast.makeText(LoginActivity.this, "Bienvenido/a " + c.getNombre(), Toast.LENGTH_SHORT).show();

        if (c == null) {
            Toast.makeText(LoginActivity.this, "Los datos no coinciden con ningún cliente", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            intent.putExtra("cliente", c);
            startActivity(intent);
            saveOnPreferences(usuario.getText().toString(), password.getText().toString());
        }
    }
}
