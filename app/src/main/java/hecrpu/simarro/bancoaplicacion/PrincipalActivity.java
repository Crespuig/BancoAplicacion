package hecrpu.simarro.bancoaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hecrpu.simarro.bancoaplicacion.activity.ConfiguracionActivity;
import hecrpu.simarro.bancoaplicacion.activity.GlobalActivity;
import hecrpu.simarro.bancoaplicacion.activity.PreferenceActivity;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Global;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity {
    Cliente cliente;
    Activity_Fragment_Global fragment_global;
    ConstraintLayout constraintLayout;
    SoundPool soundPool;
    int sonido_de_reproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        constraintLayout = (ConstraintLayout) this.findViewById(R.id.principalLayout);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        fragment_global = new Activity_Fragment_Global(cliente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView textView = (TextView) findViewById(R.id.txtSubtitulo);
        textView.setText(cliente.getNombre());

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_de_reproduccion = soundPool.load(this, R.raw.sound_short, 1);


    }

    public void audioSoundPool(View view){
        soundPool.play(sonido_de_reproduccion, 1, 1, 1, 0, 0);
    }

    public void audioMediaPlayer(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound_long);
        mediaPlayer.start();
    }

    public void onClickBtnSalir(View view) {
        audioSoundPool(view);
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
        intent.putExtra("cliente", cliente);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_global:
                cambiaFragment(R.id.fragment, fragment_global);
                constraintLayout.setVisibility(View.GONE);
                return true;
            case R.id.action_ingresos:
                Toast.makeText(getApplicationContext(), "Ingresos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_transferencias:
                Intent intent = new Intent(PrincipalActivity.this, TransferActivity.class);
                intent.putExtra("cliente", cliente);
                startActivityForResult(intent, 0);
                return true;
            case R.id.action_cambiarClave:
                Intent intent2 = new Intent(PrincipalActivity.this, CambiarClave.class);
                intent2.putExtra("cliente", cliente);
                startActivityForResult(intent2, 0);
                return true;
            /*case R.id.action_preferencias:
                Intent intent3 = new Intent(PrincipalActivity.this, PreferenceActivity.class);
                intent3.putExtra("cliente", cliente);
                startActivityForResult(intent3, 0);*/
            case R.id.action_configuracion:
                Intent intent4 = new Intent(PrincipalActivity.this, ConfiguracionActivity.class);
                intent4.putExtra("cliente", cliente);
                startActivityForResult(intent4, 0);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}