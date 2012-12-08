package com.jshaw.greeknetwork;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessageActivity extends Activity {

	private Button send;
	private EditText text;
	private String number;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_message);
		number = getIntent().getStringExtra(ProfileActivity.NUMBER_EXTRA);
		
		send = (Button)findViewById(R.id.send);
		text = (EditText)findViewById(R.id.message);
		
		send.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View view)
			{              
                String m = text.getText().toString();
                
                if (number.length()>0)
                {
	                if(m.length()>0)                
	                {
	                	sendSMS(number, m);                
	                }
	                else
	                {
	                	Toast.makeText(getBaseContext(),"Message cannot be empty", Toast.LENGTH_LONG).show();
	                }
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Member does not have a phone number", Toast.LENGTH_LONG).show();
                }     
			}

		});
	}
	
	private void sendSMS(String number, String m) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(SendMessageActivity.this, ReceiveMessage.class), 0);                
	    SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(number, null, m, pi, null);   
		
	}
}
