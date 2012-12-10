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
import android.widget.EditText;
import android.widget.Toast;

public class CreateMemberActivity extends Activity {

	EditText name;
	EditText year;
	EditText pos;
	EditText comments;
	EditText number;
	GreekHelper helper;
	
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.create_member);
		
		helper = new GreekHelper(this);
		
		name = (EditText)findViewById(R.id.member_name);
		year = (EditText)findViewById(R.id.member_year);
		pos = (EditText)findViewById(R.id.member_pos);
		comments = (EditText)findViewById(R.id.member_comments);
		number = (EditText) findViewById(R.id.member_number);
		
		Button accept = (Button) findViewById(R.id.accept_member);
		accept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if(name.getText().toString().length()==0)
				{
					Toast.makeText(view.getContext(), "No name entered", Toast.LENGTH_SHORT).show();
				}
				
				Member mem = new Member(name.getText().toString(), year.getText().toString(), pos.getText().toString(), comments.getText().toString(), number.getText().toString());
				helper.insertMember(mem);
				
				Toast.makeText(view.getContext(), "New Member Saved", Toast.LENGTH_SHORT).show();
				
				String m;	      
				m = "&p"+mem.getName()+":"+mem.getYear()+":"+mem.getPosition()+":"+mem.getComments()+":"+mem.getNumber()+":";
		
				Cursor members = helper.getMembers();
				
				members.moveToFirst();
				while(!members.isAfterLast())
				{
					String number = helper.getNumber(members);
					
					PendingIntent pi = PendingIntent.getActivity(CreateMemberActivity.this, 0, new Intent(CreateMemberActivity.this, MessageReceiver.class), 0);     
					SmsManager sms = SmsManager.getDefault();
			        sms.sendTextMessage(number, null, m, pi, null);
			        
			        members.moveToNext();
				}
		    	Toast.makeText(getBaseContext(),"Members Notified", Toast.LENGTH_SHORT).show();
		    	finish();
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
