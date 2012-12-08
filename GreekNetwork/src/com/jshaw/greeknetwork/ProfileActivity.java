package com.jshaw.greeknetwork;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private TextView name;
	private TextView year;
	private TextView pos;
	private TextView comments;
	private TextView number;
	private Button send;
	
	private String id;
	private GreekHelper helper;
	private Cursor c;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.profile);
		
		id = getIntent().getStringExtra(MemberListActivity.ID_EXTRA);
		
		helper = new GreekHelper(this);
		c = helper.getMember(id);
		c.moveToFirst();
		
		name = (TextView)findViewById(R.id.name);
		year = (TextView)findViewById(R.id.year);
		pos = (TextView)findViewById(R.id.position);
		comments = (TextView)findViewById(R.id.comments);
		number = (TextView)findViewById(R.id.number);
		send = (Button)findViewById(R.id.send_message);
		
		name.setText(helper.getName(c));
		year.setText(helper.getYear(c));
		pos.setText(helper.getPos(c));
		comments.setText(helper.getComments(c));
		number.setText(helper.getNumber(c));
		
		send.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfileActivity.this, SendMessageActivity.class);
				startActivity(i);
			}	
        });
		
		c.close();
		helper.close();
	}
}
