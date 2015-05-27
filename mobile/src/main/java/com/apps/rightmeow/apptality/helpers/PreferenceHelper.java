package com.apps.rightmeow.apptality.helpers;

import com.apps.rightmeow.apptality.AppTalityApp;

/**
 * Created by AKiniyalocts on 5/27/15.
 */
public class PreferenceHelper {
  public final static String PLATFORM_KEY = "platform:key";


  public static boolean isPlaystation(){
    return AppTalityApp.getSharedPreferences().getBoolean(PLATFORM_KEY, true);
  }

  public static void setPlatformKey(boolean isPlaystation){
    AppTalityApp.getSharedPreferences().edit().putBoolean(PLATFORM_KEY, isPlaystation).apply();
  }
}
