package com.jshaw.greeknetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

	public static final String MESSAGE_EXTRA = "com.jshaw.greeknetwork.MESSAGE";
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{    		
        Bundle bundle = intent.getExtras();        
        GreekHelper db = new GreekHelper(context);
        if (bundle != null)
        {
            Object[] sms = (Object[]) bundle.get("pdus");
            
            for (int i=0; i<sms.length; i++)
            {   	
                SmsMessage msg = SmsMessage.createFromPdu((byte[])sms[i]);
                String message = msg.getDisplayMessageBody().toString();
                if(message.charAt(0)=='m')
                {
                	db.insertMessage(msg.getOriginatingAddress(), message);
                }
                if(message.charAt(0)=='e')
                {
                	
                	db.insertMessage(msg.getOriginatingAddress(), message);
                }
                if(message.charAt(0)=='p')
                {
                	db.insertMessage(msg.getOriginatingAddress(), message);
                }
            }
            
            db.close();
            
            Toast.makeText(context, "Message received", Toast.LENGTH_SHORT).show();
            
            Intent i = new Intent(context, MessageListActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            
        }                         
    }
	
	public Member extractMember(String msg)
	{
		String bites[];
		for(int i=0; i<msg.length(); i++)
		{
			
		}
	}
	
	public Member extractEvent(String msg)
	{
		
	}

}
