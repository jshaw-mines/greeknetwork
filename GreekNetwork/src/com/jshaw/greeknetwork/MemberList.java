package com.jshaw.greeknetwork;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MemberList extends ListActivity {

	private Cursor model;
	private MemberAdapter adapter;
	private GreekHelper helper;
	
	public final static String ID_EXTRA="com.jshaw.greeknetwork._ID";
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		this.setContentView(R.layout.member_list);
		
		helper = new GreekHelper(this);
		
		model = helper.getMembers();
		
    	startManagingCursor(model);
    	adapter = new MemberAdapter(model);
    	setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id)
	{
		 Intent i = new Intent(MemberList.this, ProfileActivity.class);
		 i.putExtra(ID_EXTRA, String.valueOf(id));
		 startActivity(i);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		helper.close();
	}
	
	class MemberAdapter extends CursorAdapter 
  	{  	
		MemberAdapter(Cursor c) 
		{
			super(MemberList.this, c);
		}
	  	
		@Override
		public void bindView(View row, Context ctxt, Cursor c) 
		{
			MemberHolder holder=(MemberHolder)row.getTag();
			holder.populateFrom(c, helper);
		}
	  
		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) 
		{
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.member_row, parent, false);
			MemberHolder holder=new MemberHolder(row);
			row.setTag(holder);
			return(row);
		}
  	}
  
	static class MemberHolder 
	{
		private TextView name=null;
		private TextView year=null;
		private TextView position=null;
	  
		MemberHolder(View row) 
		{
			name=(TextView)row.findViewById(R.id.member_name);
			year=(TextView)row.findViewById(R.id.year);
			position=(TextView)row.findViewById(R.id.position);
		}
	  
		void populateFrom(Cursor c, GreekHelper helper) 
		{
			name.setText(helper.getName(c));
			year.setText(helper.getYear(c));
			position.setText(helper.getPos(c));
		  
		}
	}
}
