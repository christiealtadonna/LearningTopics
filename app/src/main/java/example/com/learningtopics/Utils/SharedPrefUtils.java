package example.com.learningtopics.Utils;

/**
 * Created by christiealtadonna on 4/22/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefUtils {
    private static final String USERNAME ="username";
    private static final String PASSWORD ="password";

    public static String getUsernamePreference(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return prefs.getString(USERNAME, "");
    }

    public static void setUsernamePreference(Context context, String value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        prefs.edit().putString(USERNAME, value).apply();
    }

    public static String getPasswordPreference(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return prefs.getString(PASSWORD, "");
    }

    public static void setPasswordPreference(Context context, String value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        prefs.edit().putString(PASSWORD, value).apply();
    }


}
