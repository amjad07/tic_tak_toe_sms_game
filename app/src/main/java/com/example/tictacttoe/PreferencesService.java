package com.example.tictacttoe;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesService {

    private static final String PREFS_NAME = "goldPocket";
    @SuppressLint("StaticFieldLeak")
    private static PreferencesService mSingleton = new PreferencesService();
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;



    private PreferencesService() {

    }

    public static PreferencesService instance() {
        return mSingleton;
    }

    public static void init(Context context) {

        mContext = context;
    }

    private SharedPreferences getPrefs() {
        return mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    private void ClearPreference() {
        SharedPreferences settings = getPrefs();
        settings.edit().clear().apply();
    }



    public String getFirstPlayer(){
        return getPrefs().getString("first", "");
    }

    public void setFirstPlayer(String firstPlayer){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString("first", firstPlayer);
        editor.apply();
    }

    public String getSecondPlayer(){
        return getPrefs().getString("second", "");
    }

    public void setSecondPlayer(String secondPlayer){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString("second", secondPlayer);
        editor.apply();
    }

    public String getFirstNum(){
        return getPrefs().getString("firstNum", "");
    }

    public void setFirstNum(String firstNum){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString("firstNum", firstNum);
        editor.apply();
    }


    public String getSecondNum(){
        return getPrefs().getString("secondNum", "");
    }

    public void setSecondNum(String secondNum){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString("secondNum", secondNum);
        editor.apply();
    }
}
