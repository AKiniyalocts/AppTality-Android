package com.apps.rightmeow.apptality.controller;

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

/**
 * Created by AKiniyalocts on 4/21/15.
 */
public class FatalityAdapter extends RecyclerView.Adapter<FatalityAdapter.FatalityViewHolder> {

  private LayoutInflater mInflater;
  private Roster.Characters.Fatality[] fatalities;

  public FatalityAdapter(Roster.Characters.Fatality[] fatalities) {
    mInflater = LayoutInflater.from(AppTalityApp.getContext());
    this.fatalities = fatalities;
  }

  @Override
  public FatalityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View returnView = mInflater.inflate(R.layout.fatality_item, parent, false);
    return new FatalityViewHolder(returnView);
  }

  @Override public void onBindViewHolder(FatalityViewHolder holder, int position) {
    Roster.Characters.Fatality fatality = fatalities[position];

    holder.mCombo.setText(fatality.getFormattedCode());
    holder.mTitle.setText(fatality.name);
    holder.mLoc.setText(" (" + fatality.location + ")");
  }

  @Override public int getItemCount() {
    return fatalities.length;
  }

  public static class FatalityViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.fatality_combo)TextView mCombo;
    @InjectView(R.id.fatality_title)TextView mTitle;
    @InjectView(R.id.fatality_loc)TextView mLoc;

    public FatalityViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);

    }
  }
}
