package com.jshaw.greeknetwork;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jshaw.greeknetwork.MemberListActivity.MemberAdapter;
import com.jshaw.greeknetwork.MemberListActivity.MemberHolder;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EventListActivity extends ListActivity {

	private Cursor model;
	private EventAdapter adapter;
	private GreekHelper helper;
	private Calendar cal;
	
	public final static String ID_EXTRA="com.jshaw.greeknetwork._ID";
	private final long DAY_LENGTH=86400000;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		this.setContentView(R.layout.member_list);
		
		cal = Calendar.getInstance();
		
		helper = new GreekHelper(this);
		model = helper.getEvents(cal.getTimeInMillis()-DAY_LENGTH);
		Date date = new Date();
		
    	startManagingCursor(model);
    	adapter = new EventAdapter(model);
    	setListAdapter(adapter);
    	
    	Button button = (Button)findViewById(R.id.new_event);
    	button.setOnClickListener(new OnClickListener ()
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(EventListActivity.this, CreateEventActivity.class);
				startActivity(i);
			}      	
        });
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id)
	{
		 Intent i = new Intent(EventListActivity.this, EventActivity.class);
		 i.putExtra(ID_EXTRA, String.valueOf(id));
		 startActivity(i);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(model!=null)
		{
			stopManagingCursor(model);
			model.close();
		}
		helper.close();
	}
	
	class EventAdapter extends CursorAdapter 
  	{  	
		EventAdapter(Cursor c) 
		{
			super(EventListActivity.this, c);
		}
	  	
		@Override
		public void bindView(View row, Context ctxt, Cursor c) 
		{
			EventHolder holder=(EventHolder)row.getTag();
			holder.populateFrom(c, helper);
		}
	  
		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) 
		{
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.event_row, parent, false);
			EventHolder holder=new EventHolder(row);
			row.setTag(holder);
			return(row);
		}
  	}
  
	static class EventHolder 
	{
		private TextView name=null;
		private TextView date=null;
	  
		EventHolder(View row) 
		{
			name=(TextView)row.findViewById(R.id.event_name);
			date=(TextView)row.findViewById(R.id.event_date);
		}
	  
		void populateFrom(Cursor c, GreekHelper helper) 
		{
			name.setText(helper.getName(c));
			date.setText(DateFormat.getInstance().format(new Date(helper.getDate(c))));
		}
	}
}
