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
                db.insertMessage(msg.getOriginatingAddress(), msg.getDisplayMessageBody().toString());
            }
            
            db.close();
            
            Toast.makeText(context, "Message received", Toast.LENGTH_SHORT).show();
            
        }                         
    }

}
