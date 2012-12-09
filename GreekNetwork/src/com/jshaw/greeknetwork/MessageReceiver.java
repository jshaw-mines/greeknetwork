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
                if(message.charAt(message.length()-1) == '`')
                {
		            if(message.charAt(0)=='m')
		            {
		            	db.insertMessage(msg.getOriginatingAddress(), message);
		            	db.close();
		            	abortBroadcast();
		            	
		            	Intent in = new Intent(context, MessageListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
		            if(message.charAt(0)=='e')
		            {		            	
		            	db.insertEvent(extractEvent(message));
		            	db.close();
		            	abortBroadcast();
		            	
		            	Intent in = new Intent(context, EventListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
		            if(message.charAt(0)=='p')
		            {
		            	db.insertMember(extractMember(message));
		            	db.close();
		            	abortBroadcast();
		            	
		            	Intent in = new Intent(context, MemberListActivity.class);
		                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                context.startActivity(in);
		            }
                }
                else
                {
                	db.close();
                	return;
                }
            }
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
				i++;
			}
			else
			{
				val += val+msg.charAt(i);
			}
		}
		
		return new Member(vals[0], vals[1], vals[2], vals[3], vals[4]);
	}
	
	public Event extractEvent(String msg)
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
				i++;
			}
			else
			{
				val += val+msg.charAt(i);
			}
		}
		
		return new Event(vals[0], Long.valueOf(vals[1]), vals[2]);
	}

}
