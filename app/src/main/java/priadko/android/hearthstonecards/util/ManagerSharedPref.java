package priadko.android.hearthstonecards.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Set;

import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;

public class ManagerSharedPref {
    private static ManagerSharedPref instance;
    private SharedPreferences sharedPreferences;

    private ManagerSharedPref(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static ManagerSharedPref with(Context context) {
        if (instance == null) {
            instance = new ManagerSharedPref(context);
        }
        return instance;
    }

    public static ManagerSharedPref with(Fragment fragment) {
        if (instance == null) {
            instance = new ManagerSharedPref(fragment.getContext());
        }
        return instance;
    }

    public static ManagerSharedPref with(AppCompatActivity activity) {
        if (instance == null) {
            instance = new ManagerSharedPref(activity);
        }
        return instance;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INFO
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void saveInfo(ResponseInfo info) {
        sharedPreferences.edit().putStringSet(INFO.CLASSES.getName(), info.getClasses()).apply();
        sharedPreferences.edit().putStringSet(INFO.SETS.getName(), info.getSets()).apply();
        sharedPreferences.edit().putStringSet(INFO.STANDARD.getName(), info.getStandard()).apply();
        sharedPreferences.edit().putStringSet(INFO.WILD.getName(), info.getWild()).apply();
        sharedPreferences.edit().putStringSet(INFO.TYPES.getName(), info.getTypes()).apply();
        sharedPreferences.edit().putStringSet(INFO.FACTIONS.getName(), info.getFactions()).apply();
        sharedPreferences.edit().putStringSet(INFO.QUALITIES.getName(), info.getQualities()).apply();
        sharedPreferences.edit().putStringSet(INFO.RACES.getName(), info.getRaces()).apply();
        sharedPreferences.edit().putString(INFO.VERSION.getName(), info.getPatch()).apply();
    }

    public boolean isInfoExist(String version) {
        return sharedPreferences.getString(INFO.VERSION.getName(), "").equals(version);
    }

    public Set<String> getInfo(INFO info) {
        return sharedPreferences.getStringSet(info.getName(), null);
    }

    public enum INFO {
        CLASSES("CLASSES"),
        SETS("SETS"),
        STANDARD("STANDARD"),
        WILD("WILD"),
        TYPES("TYPES"),
        FACTIONS("FACTIONS"),
        QUALITIES("QUALITIES"),
        RACES("RACES"),
        VERSION("VERSION");

        INFO(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }
    }

}
