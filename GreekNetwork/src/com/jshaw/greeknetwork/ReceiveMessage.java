package com.jshaw.greeknetwork;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessage extends Activity {

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_feed);
		
		TextView mes = (TextView)findViewById(R.id.display_message);
		
		mes.setText(getIntent().getStringExtra(MessageReceiver.MESSAGE_EXTRA));
	}
}
