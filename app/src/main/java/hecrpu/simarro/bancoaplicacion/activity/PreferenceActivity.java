package hecrpu.simarro.bancoaplicacion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.view.menu.ListMenuPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import hecrpu.simarro.bancoaplicacion.PrincipalActivity;
import hecrpu.simarro.bancoaplicacion.R;
import hecrpu.simarro.bancoaplicacion.fragment.Activity_Fragment_Movimiento;
import hecrpu.simarro.bancoaplicacion.pojo.Utils;

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



            final ListPreference idioma = (ListPreference) findPreference("idioma");
            Utils utils = new Utils();

            if (idioma.getValue().equals("Español")){
                Utils.setLocale(context.getActivity(), "es");
            } else if (idioma.getValue().equals("Inglés")){
                Utils.setLocale(context.getActivity(), "en");
            }else if (idioma.getValue().equals("Francés")){
                Utils.setLocale(context.getActivity(), "fr");
            }

            /*idioma.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    switch (R.array.codigoIdioma) {
                        case 0:
                            Locale español = new Locale("es", "ES");
                            Locale.setDefault(español);
                            Configuration config_es = new Configuration();
                            config_es.locale = español;
                            getResources().updateConfiguration(config_es, getResources().getDisplayMetrics());
                            break;
                        case 1:
                            Locale ingles = new Locale("en", "EN");
                            Locale.setDefault(ingles);
                            Configuration config_en = new Configuration();
                            config_en.locale = ingles;
                            getResources().updateConfiguration(config_en, getResources().getDisplayMetrics());
                            break;
                        case 2:
                            Locale frances = new Locale("fr", "FR");
                            Locale.setDefault(frances);
                            Configuration config_fr = new Configuration();
                            config_fr.locale = frances;
                            getResources().updateConfiguration(config_fr, getResources().getDisplayMetrics());
                            break;
                    }
                    return false;
                }

            });*/
        }


    }

}