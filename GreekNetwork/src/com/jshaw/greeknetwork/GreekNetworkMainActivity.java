package com.jshaw.greeknetwork;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GreekNetworkMainActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button member_list = (Button)findViewById(R.id.view_member_list);
        member_list.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GreekNetworkMainActivity.this, MemberListActivity.class);
				startActivity(i);
			}	
        });
        
        Button event_list = (Button)findViewById(R.id.view_event_list);
        event_list.setOnClickListener(new OnClickListener ()
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GreekNetworkMainActivity.this, EventListActivity.class);
				startActivity(i);
			}      	
        });
        
        Button messages = (Button)findViewById(R.id.view_message_list);
        messages.setOnClickListener(new OnClickListener ()
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GreekNetworkMainActivity.this, MessageListActivity.class);
				startActivity(i);
			}      	
        });
    }
}
