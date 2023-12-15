package com.codersworld.jplibs.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.codersworld.jplibs.beans.UserBean;
import com.codersworld.jplibs.utils.CommonMethods;
import com.codersworld.jplibs.utils.Tags;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class UserSessions {
    public static SharedPreferences mPrefs = null;
    public static SharedPreferences.Editor prefsEditor = null;
    public static Context mContext;

    public UserSessions() {

    }

    public UserSessions(Context ctx) {
        this.mContext = ctx;
        initSession();
    }

    public static void initSession() {
        if (mPrefs == null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        }
        if (prefsEditor == null) {
            prefsEditor = mPrefs.edit();
        }
    }





    public static void saveAppStatus(Context context, String str) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        //Log.e("STTTRR", str + " : ");
        editor.putString(Tags.SB_APP_STATUS, str);
        editor.commit();
    }

    public static String getAppStatus(Context context) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString(Tags.SB_APP_STATUS, "");
    }

    public static void saveOpened(Context context, int str) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(Tags.SB_IS_OPENED, str);
        editor.commit();
    }

    public static int getOpened(Context context) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getInt(Tags.SB_IS_OPENED, 0);
    }

    public static void saveFcmToken(Context context, String str) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Tags.SB_FCM_ID, CommonMethods.isValidString(str) ? str : "");
        editor.commit();
    }

    public static String getFcmToken(Context context) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString(Tags.SB_FCM_ID, "NO GCM");
    }


    public static void saveUpdate(Context context, int str) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt("isUpdate", str);
        editor.commit();
    }

    public static int getUpdate(Context context) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getInt("isUpdate", 0);
    }

    public static void saveAction(Context context, String strKey, String strValue) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(strKey, strValue);
        editor.commit();
    }

    public static String getAction(Context context, String strKey) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return mPrefs.getString(strKey, "");
    }

    public void saveLanguage(Context context, String str) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Tags.SB_LANGUAGE, CommonMethods.isValidString(str) ? str : "");
        editor.commit();

    }

    public String getLanguage(Context supportActivity) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(supportActivity);
        return mPrefs.getString(Tags.SB_LANGUAGE, "1");
    }
    public String getLanguage(Activity supportActivity) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(supportActivity);
        return mPrefs.getString(Tags.SB_LANGUAGE, "1");
    }

    public void saveAccessToken( Activity mainActivity,  String s) {
        mContext = mainActivity;
        initSession();
        prefsEditor.putString(Tags.SB_ACCESS_TOKEN, s);
        prefsEditor.commit();
    }

     public String getAccessToken( Activity mainActivity) {
        mContext = mainActivity;
        initSession();
        return mPrefs.getString(Tags.SB_ACCESS_TOKEN, "");
    }
    public static void saveUserInfo( Activity mainActivity,  UserBean s) {
      try {
          mContext = mainActivity;
          initSession();
          prefsEditor.putString("user_info", new Gson().toJson(s));
          prefsEditor.commit();
      }catch (Exception e){
          e.printStackTrace();
      }
    }


     public   UserBean getUserInfo( Activity mainActivity) {
        mContext = mainActivity;
        initSession();
        if (CommonMethods.isValidString(mPrefs.getString("user_info", ""))){
            return new Gson().fromJson(mPrefs.getString("user_info", ""),UserBean.class);
        }else {
            return null;
        }
    }
    public void saveDBStatus( Activity mainActivity,  String s) {
        mContext = mainActivity;
        initSession();
        prefsEditor.putString("db_status", s);
        prefsEditor.commit();
    }

     public String getDBStatus( Activity mainActivity) {
        mContext = mainActivity;
        initSession();
        return mPrefs.getString("db_status", "");
    }
}
