package com.apps.rightmeow.apptality.controller;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.apps.rightmeow.apptality.AppTalityApp;
import com.apps.rightmeow.apptality.R;
import com.apps.rightmeow.apptality.model.Roster;
import com.apps.rightmeow.apptality.view.NestedLayoutManager;
import java.util.List;

/**
 * Created by AKiniyalocts on 4/21/15.
 */
public class RosterAdapter extends RecyclerView.Adapter<RosterAdapter.RosterViewHolder> {
  private List<Roster.Characters> mCharacters;
  private LayoutInflater mInflater;


  public RosterAdapter(List<Roster.Characters> mCharacters) {
    this.mCharacters = mCharacters;
    mInflater = LayoutInflater.from(AppTalityApp.getContext());
  }

  @Override
  public RosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View returnView = mInflater.inflate(R.layout.roster_item, parent, false);

    return new RosterViewHolder(returnView);
  }

  @Override public void onBindViewHolder(RosterViewHolder holder, int position) {
    Roster.Characters character = mCharacters.get(position);

    holder.mName.setText(character.name);
    holder.mFatalities.setAdapter(new FatalityAdapter(mCharacters.get(position).fatalities));


  }

  @Override public int getItemCount() {
    return mCharacters.size();
  }

  public class RosterViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.name)TextView mName;
    @InjectView(R.id.fatality_recycler)RecyclerView mFatalities;

    public RosterViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
      mFatalities.setLayoutManager(new NestedLayoutManager(AppTalityApp.getContext(),
          LinearLayoutManager.VERTICAL, false));
    }
  }
}
