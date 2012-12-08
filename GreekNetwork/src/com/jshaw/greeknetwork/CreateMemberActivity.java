package com.jshaw.greeknetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
				Member m = new Member(name.getText().toString(), year.getText().toString(), pos.getText().toString(), comments.getText().toString(), number.getText().toString());
				helper.insertMember(m);
				
				Toast.makeText(view.getContext(), "New Member Saved", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(CreateMemberActivity.this, MemberListActivity.class);
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
