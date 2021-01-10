package hecrpu.simarro.bancoaplicacion.activity;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import hecrpu.simarro.bancoaplicacion.PrincipalActivity;
import hecrpu.simarro.bancoaplicacion.R;

public class ConfiguracionActivity extends AppCompatActivity implements View.OnClickListener{

    CheckBox musica;
    CheckBox video;
    Button v_btnGuardar;
    Button btnPreferencias;
    SharedPreferences prefs;
    SoundPool soundPool;
    int sonido_de_reproduccion;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        // Declaramos los elementos con los que vamos a trabajar
        musica = (CheckBox) findViewById(R.id.checkBoxMusica);
        video = (CheckBox) findViewById(R.id.checkBoxVideo);
        v_btnGuardar = (Button) findViewById(R.id.btnGuardar);

        // Obtenemos la referencia a la coleccion de preferencias
        prefs = getSharedPreferences("preferenciasbancarias", Context.MODE_PRIVATE);

        v_btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (musica.isChecked()){
                    mediaPlayer.start();
                }else{
                    mediaPlayer.stop();
                }

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("musica", musica.isChecked());
                editor.putBoolean("video", video.isChecked());
                editor.commit();
            }
        });

        btnPreferencias = (Button) findViewById(R.id.btnPrefs);
        btnPreferencias.setOnClickListener(this);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido_de_reproduccion = soundPool.load(this, R.raw.sound_short, 1);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_long);



    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivityForResult(intent, 0);
    }
}