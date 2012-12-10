package com.jshaw.greeknetwork;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity {

	public static final String NUMBER_EXTRA = "com.jshaw.greeknetwork.NUMBER";
	
	private TextView name;
	private TextView year;
	private TextView pos;
	private TextView comments;
	private TextView number;
	private Button send;
	private Button edit;
	
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
		edit = (Button)findViewById(R.id.edit_member);
		
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
				i.putExtra(ProfileActivity.NUMBER_EXTRA, helper.getNumber(c));
				startActivity(i);
			}
        });	
		
		edit.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfileActivity.this, CreateMemberActivity.class);
				i.putExtra(MemberListActivity.ID_EXTRA, id);
				startActivity(i);
			}
        });	
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		c.close();
		helper.close();
	}
}
