package com.jshaw.greeknetwork;

import android.app.ListActivity;
import android.os.Bundle;

public class EventListActivity extends ListActivity {

	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		this.setContentView(R.layout.event_list);
	}
}