package com.jshaw.greeknetwork;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GreekNetworkMain extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        
        Button member_list = (Button)findViewById(R.id.view_member_list);
        member_list.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GreekNetworkMain.this, MemberList.class);
				startActivity(i);
			}	
        });
        
        Button event_list = (Button)findViewById(R.id.view_event_list);
        event_list.setOnClickListener(new OnClickListener ()
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GreekNetworkMain.this, EventList.class);
				startActivity(i);
			}      	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
