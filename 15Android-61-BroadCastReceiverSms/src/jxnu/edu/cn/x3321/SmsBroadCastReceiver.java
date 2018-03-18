package jxnu.edu.cn.x3321;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		/*
		 *Android设备收到的邮件是以pdu（protocol description unit）协议来封装的
		 * 
		 */
		Object[] bs=(Object[])intent.getExtras().get("pdus");
		//取出第一个
		Object pdu=bs[0];
		
		SmsMessage sms=SmsMessage.createFromPdu((byte[])pdu);
		String address=sms.getOriginatingAddress();
		String body=sms.getMessageBody();
		
		//如果sms包含一些敏感信息，我们可以停止接受广播
		if(body.contains("sb")){
			System.out.println("收到一条敏感信息！");
			abortBroadcast();//终止接收广播
		}else{
			System.out.println("address:"+address+"\n"+"body:"+body);
		}
	}

}
