package com.jshaw.greeknetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MessageReceiver extends BroadcastReceiver {

	public static final String MESSAGE_EXTRA = "com.jshaw.greeknetwork.MESSAGE";
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{    
		
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "";            
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];       
            
            for (int i=0; i<msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                str += "Message from " + msgs[i].getOriginatingAddress();                     
                str += ": ";
                str += msgs[i].getMessageBody().toString();    
            }
            
            intent.putExtra(MessageReceiver.MESSAGE_EXTRA, str);
            context.startActivity(intent);
        }                         
    }

}
