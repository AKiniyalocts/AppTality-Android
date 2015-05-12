package com.apps.rightmeow.apptality.model;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by AKiniyalocts on 4/21/15.
 */
public class MKAPI {

  public final static String SERVER = "http://104.236.204.178";

  public interface RosterService{
    @GET("/.private/mkx/mkx.json")
    void getRoster(Callback<Roster> cb);
  }
}
