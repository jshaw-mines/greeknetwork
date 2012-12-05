package com.jshaw.greeknetwork;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity {

	private TextView name;
	private TextView date;
	private TextView details;
	
	private String id;
	private GreekHelper helper;
	private Cursor c;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.event);
		
		id = getIntent().getStringExtra(EventListActivity.ID_EXTRA);
		
		helper = new GreekHelper(this);
		c = helper.getEvent(id);
		c.moveToFirst();
		
		name = (TextView)findViewById(R.id.name);
		date = (TextView)findViewById(R.id.date);
		details = (TextView)findViewById(R.id.detials);
		
		name.setText(helper.getName(c));
		date.setText(DateFormat.getInstance().format(new Date(helper.getDate(c))));
		details.setText(helper.getPos(c));
		
		c.close();
		helper.close();
	}
}
