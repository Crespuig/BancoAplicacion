package hecrpu.simarro.bancoaplicacion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
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
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.view.menu.ListMenuPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import hecrpu.simarro.bancoaplicacion.LoginActivity;
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

            ListPreference idioma = (ListPreference) findPreference("idioma");
            idioma.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    switch (o.toString()) {
                        case "0":
                            OnBackPressedCallback back = new OnBackPressedCallback(true) {
                                @Override
                                public void handleOnBackPressed() {
                                    Toast.makeText(context.getActivity(), "ESPAÑOL", Toast.LENGTH_SHORT).show();
                                    Locale español = new Locale("es", "ES");
                                    Locale.setDefault(español);
                                    Configuration config_es = new Configuration();
                                    config_es.locale = español;
                                    getResources().updateConfiguration(config_es, getResources().getDisplayMetrics());
                                }
                            };
                            back.handleOnBackPressed();
                            break;
                        case "1":
                            OnBackPressedCallback back2 = new OnBackPressedCallback(true) {
                                @Override
                                public void handleOnBackPressed() {
                                    Toast.makeText(context.getActivity(), "ENGLISH", Toast.LENGTH_SHORT).show();
                                    Locale ingles = new Locale("en", "EN");
                                    Locale.setDefault(ingles);
                                    Configuration config_en = new Configuration();
                                    config_en.locale = ingles;
                                    getResources().updateConfiguration(config_en, getResources().getDisplayMetrics());
                                }
                            };
                            back2.handleOnBackPressed();
                            break;
                        case "2":
                            OnBackPressedCallback back3 = new OnBackPressedCallback(true) {
                                @Override
                                public void handleOnBackPressed() {
                                    Toast.makeText(context.getActivity(), "FRENCH", Toast.LENGTH_SHORT).show();
                                    Locale frances = new Locale("fr", "FR");
                                    Locale.setDefault(frances);
                                    Configuration config_fr = new Configuration();
                                    config_fr.locale = frances;
                                    getResources().updateConfiguration(config_fr, getResources().getDisplayMetrics());
                                }
                            };
                            back3.handleOnBackPressed();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + o.toString());
                    }
                    return false;
                }

            });

            ListPreference fuente = (ListPreference) findPreference("fuente");
            fuente.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    switch (o.toString()){
                        case "LSE":
                            Toast.makeText(context.getActivity(), "LIBERATION SERIF", Toast.LENGTH_SHORT).show();

                            break;
                        case "CAS":
                            Toast.makeText(context.getActivity(), "CASUAL", Toast.LENGTH_SHORT).show();

                            break;
                        case "CUR":
                            Toast.makeText(context.getActivity(), "CURSIVE", Toast.LENGTH_SHORT).show();

                            break;
                        case "ANV":
                            Toast.makeText(context.getActivity(), "MONOSPACE", Toast.LENGTH_SHORT).show();

                            break;
                        case "DOS":
                            Toast.makeText(context.getActivity(), "CHILANKA", Toast.LENGTH_SHORT).show();

                            break;
                    }

                    return false;
                }
            });

            ListPreference color = (ListPreference) findPreference("color");
            color.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    switch (o.toString()){
                        case "NAR":
                            Toast.makeText(context.getActivity(), "NARANJA", Toast.LENGTH_SHORT).show();
                            int naranja = getResources().getColor(R.color.colorPrimary);
                            
                            break;
                        case "AZU":
                            Toast.makeText(context.getActivity(), "AZUL", Toast.LENGTH_SHORT).show();
                            int azul = getResources().getColor(R.color.azul);
                            break;
                        case "GRI":
                            Toast.makeText(context.getActivity(), "GRIS", Toast.LENGTH_SHORT).show();
                            int gris = getResources().getColor(R.color.gris);
                            break;
                        case "NEG":
                            Toast.makeText(context.getActivity(), "NEGRO", Toast.LENGTH_SHORT).show();
                            int negro = getResources().getColor(R.color.negro);
                            break;
                        case "ROJ":
                            Toast.makeText(context.getActivity(), "ROJO", Toast.LENGTH_SHORT).show();
                            int rojo = getResources().getColor(R.color.rojo);
                            break;
                    }

                    return false;
                }
            });
        }
    }

}