package hecrpu.simarro.bancoaplicacion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;

import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;

public class PreferenceActivity extends android.preference.PreferenceActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, new PreferenciasFragment());
        ft.commit();


    }

    public static class PreferenciasFragment extends PreferenceFragment {

        SoundPool soundPool;
        int sonido_de_reproduccion;
        PreferenciasFragment context = this;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.opciones);

            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
            sonido_de_reproduccion = soundPool.load(context.getActivity(), R.raw.sound_short, 1);

            final MediaPlayer mediaPlayer = MediaPlayer.create(context.getActivity(), R.raw.sound_long);


            final CheckBoxPreference musica = (CheckBoxPreference) findPreference("musica");
            musica.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                   if (!musica.isChecked()){
                       mediaPlayer.start();
                   }else {
                       mediaPlayer.stop();
                   }
                   return true;
                }
            });
        }

    }

}