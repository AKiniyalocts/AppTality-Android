package com.apps.rightmeow.apptality;

import android.app.Application;
import android.content.Context;
import com.apps.rightmeow.apptality.model.MKAPI;
import retrofit.RestAdapter;

/**
 * Created by AKiniyalocts on 4/21/15.
 */
public class AppTalityApp extends Application {

  public static RestAdapter adapter;

  public static MKAPI.RosterService rosterService;

  public static Context context;

  public static boolean isPlaystation;

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
  }

  public static Context getContext(){
    return context;

  }

  public static RestAdapter getRestAdapter(){
    if(adapter != null){
      return adapter;
    }
    else {
      adapter = new RestAdapter.Builder()
          .setEndpoint(MKAPI.SERVER)
          .setLogLevel(RestAdapter.LogLevel.FULL)
          .build();

      return adapter;
    }
  }

  public static MKAPI.RosterService getRosterService(){
    if(rosterService != null){
      return rosterService;
    }

    else{
      rosterService = getRestAdapter().create(MKAPI.RosterService.class);

      return rosterService;
    }
  }

  public static boolean getIsPlaystation(){
    return true;
  }

}
