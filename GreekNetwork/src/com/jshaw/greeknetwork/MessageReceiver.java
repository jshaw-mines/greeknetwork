package com.jshaw.greeknetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
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
                if(message.charAt(0)=='&')
                {
		            if(message.charAt(1)=='m')
		            {
		            	db.insertMessage(msg.getOriginatingAddress(), message.substring(2));
		            	
		            	Intent in = new Intent(context, MessageListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
		            if(message.charAt(1)=='e')
		            {
		            	db.insertEvent(extractEvent(message.substring(2)));
		            	
		            	Intent in = new Intent(context, EventListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
		            if(message.charAt(1)=='p')
		            {
		            	db.insertMember(extractMember(message.substring(2)));
		            	
		            	Intent in = new Intent(context, MemberListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
		            abortBroadcast();
                }
            }
            db.close();
        }                         
    }
	
	public Member extractMember(String msg)
	{
		String vals[] = new String[5];
		String val = "";
		int j =0;
		for(int i=0; i<msg.length(); i++)
		{
			if(msg.charAt(i)==':')
			{
				vals[j] = val;
				val = "";
				j++;
			}
			else
			{
				val += msg.charAt(i);
			}
		}
		
		return new Member(vals[0], vals[1], vals[2], vals[3], vals[4]);
	}
	
	public Event extractEvent(String msg)
	{
		String vals[] = new String[3];
		String val = "";
		int j =0;
		for(int i=0; i<msg.length(); i++)
		{
			if(msg.charAt(i)==':')
			{
				vals[j] = val;
				System.out.println(vals[j]);
				val = "";
				j++;
			}
			else
			{
				val += msg.charAt(i);
			}
		}
		
		return new Event(vals[0], Long.valueOf(vals[1]), vals[2]);
	}

}
