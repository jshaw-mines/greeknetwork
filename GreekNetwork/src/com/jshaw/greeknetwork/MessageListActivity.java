package com.jshaw.greeknetwork;

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
import android.widget.Toast;

public class MessageListActivity extends ListActivity {

	private Cursor model;
	private MessageAdapter adapter;
	private GreekHelper helper;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		this.setContentView(R.layout.message_list);
		
		helper = new GreekHelper(this);
		model = helper.getMessages();
		
    	startManagingCursor(model);
    	adapter = new MessageAdapter(model);
    	setListAdapter(adapter);
    	
    	Button button = (Button)findViewById(R.id.delete_messages);
    	button.setOnClickListener(new OnClickListener ()
        {
			@Override
			public void onClick(View v) {
				helper.deleteOldMessages();
				Toast.makeText(v.getContext(), "Messages cleared", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(MessageListActivity.this, GreekNetworkMainActivity.class);
				startActivity(i);
			}      	
        });
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
	
	class MessageAdapter extends CursorAdapter 
  	{  	
		MessageAdapter(Cursor c) 
		{
			super(MessageListActivity.this, c);
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
			View row=inflater.inflate(R.layout.message, parent, false);
			EventHolder holder=new EventHolder(row);
			row.setTag(holder);
			return(row);
		}
  	}
  
	static class EventHolder 
	{
		private TextView sender=null;
		private TextView message=null;
	  
		EventHolder(View row) 
		{
			sender=(TextView)row.findViewById(R.id.sender);
			message=(TextView)row.findViewById(R.id.message_display);
		}
	  
		void populateFrom(Cursor c, GreekHelper helper) 
		{
			sender.setText(helper.getName(c));
			message.setText(helper.getYear(c));
		}
	}
}
