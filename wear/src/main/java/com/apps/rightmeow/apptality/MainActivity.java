package com.apps.rightmeow.apptality;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends Activity {

  public static final String CHAR_KEY = "char:key";

  private TextView mTextView;

  private ArrayList<String> items;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    items = getIntent().getStringArrayListExtra(CHAR_KEY);


    final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
      @Override
      public void onLayoutInflated(WatchViewStub stub) {
        mTextView = (TextView) stub.findViewById(R.id.text);
      }
    });
  }
}
