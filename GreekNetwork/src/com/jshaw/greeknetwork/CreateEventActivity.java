package com.jshaw.greeknetwork;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CreateEventActivity extends Activity {
	
	EditText name;
	DatePicker date;
	EditText details;
	GreekHelper helper;
	String id;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.create_event);
		id = getIntent().getStringExtra(MemberListActivity.ID_EXTRA);
		
		helper = new GreekHelper(this);
		
		name = (EditText)findViewById(R.id.event_name);
		date = (DatePicker)findViewById(R.id.event_date);
		details = (EditText)findViewById(R.id.event_details);
		
		if(id != null)
		{			
			Cursor c = helper.getEvent(id);
			
			Calendar cal=Calendar.getInstance();
			cal.setTimeInMillis(helper.getDate(c));

			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH);
			int day=cal.get(Calendar.DAY_OF_MONTH);

			date.updateDate(year, month, day);
			name.setText(helper.getName(c));
			details.setText(helper.getDetails(c));
			
			Button delete = (Button)findViewById(R.id.delete_event);
			delete.setVisibility(0);
			delete.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					helper.deleteEvent();
					Toast.makeText(v.getContext(), "Event Deleted", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(v.getContext(), EventListActivity.class);
					startActivity(i);
				}				
			});		
		}
		
		Button accept = (Button) findViewById(R.id.accept_event);
		accept.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View view) {
				Calendar cal = Calendar.getInstance();
				cal.set(date.getYear() + 1900, date.getMonth(), date.getDayOfMonth());
				
				Event e = new Event(name.getText().toString(), cal.getTimeInMillis(), details.getText().toString());
				helper.insertEvent(e);
				
				Toast.makeText(view.getContext(), "New Event Saved", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(CreateEventActivity.this, EventListActivity.class);
				startActivity(i);
			}
			
		});
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		helper.close();
	}
}
