package com.apps.rightmeow.apptality.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.apps.rightmeow.apptality.AppTalityApp;
import com.apps.rightmeow.apptality.R;
import com.apps.rightmeow.apptality.controller.RosterAdapter;
import com.apps.rightmeow.apptality.model.Roster;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements Callback<Roster> {

  @InjectView(R.id.roster_recycler)RecyclerView mRecycler;

  private Roster mRoster;
  private List<Roster.Characters> sChars;
  private RosterAdapter mAdapter;
  private RosterAdapter sAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    init();
  }

  private void init(){
    initRecycler();
    AppTalityApp.getRosterService().getRoster(this);
  }

  private void initRecycler(){
    mRecycler.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override public void success(Roster roster, Response response) {
    mRoster = roster;

    mAdapter = new RosterAdapter(mRoster.characters);
    mRecycler.setAdapter(mAdapter);
  }

  @Override public void failure(RetrofitError error) {

  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    SearchManager searchManager = (SearchManager) getSystemService(
        Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setQueryHint(getString(R.string.search_hint));
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String s) {

        return false;
      }

      @Override public boolean onQueryTextChange(String s) {
        new SearchTask(s).execute();
        return false;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.action_settings:
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
    return super.onOptionsItemSelected(item);
  }

  public class SearchTask extends AsyncTask<Void, Void, Void>{
    private String query;

    public SearchTask(String query) {
      this.query = query;

    }

    @Override protected Void doInBackground(Void... params) {

      sChars = new ArrayList<Roster.Characters>();

      for(Roster.Characters character: mRoster.characters){
        if(character.name.toLowerCase().contains(query)){
          sChars.add(character);
        }

        sAdapter = new RosterAdapter(sChars);
      }
      return null;
    }

    @Override protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      mRecycler.setAdapter(sAdapter);
    }
  }
}
