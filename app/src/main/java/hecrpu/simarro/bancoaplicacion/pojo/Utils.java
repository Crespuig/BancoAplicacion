package hecrpu.simarro.bancoaplicacion.pojo;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import java.util.Locale;

public class Utils {

    private static Locale defaultLocale;

    public static void setLocale(Context myContext, String languageToLoad) {

        if (defaultLocale == null) defaultLocale = Locale.getDefault(); //backup default locale

        if (languageToLoad.equals("default")) languageToLoad = defaultLocale.getLanguage();
        Log.d("UsefulFunctions", "setLocale():" + languageToLoad);

        Locale locale = new Locale(languageToLoad);
        Locale.getDefault().getDisplayLanguage();
        Configuration config = new Configuration();
        config.locale = locale;
        myContext.getResources().updateConfiguration(config,
                myContext.getResources().getDisplayMetrics());

    }

}
