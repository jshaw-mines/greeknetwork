package com.jshaw.greeknetwork;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private TextView name;
	private TextView year;
	private TextView pos;
	private TextView comments;
	private TextView number;
	
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
		
		name.setText(helper.getName(c));
		year.setText(helper.getYear(c));
		pos.setText(helper.getPos(c));
		comments.setText(helper.getComments(c));
		number.setText(helper.getNumber(c));
		
		c.close();
		helper.close();
	}
}
