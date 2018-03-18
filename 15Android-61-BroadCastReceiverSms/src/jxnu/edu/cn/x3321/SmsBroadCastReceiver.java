package jxnu.edu.cn.x3321;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		/*
		 *Android�豸�յ����ʼ�����pdu��protocol description unit��Э������װ��
		 * 
		 */
		Object[] bs=(Object[])intent.getExtras().get("pdus");
		//ȡ����һ��
		Object pdu=bs[0];
		
		SmsMessage sms=SmsMessage.createFromPdu((byte[])pdu);
		String address=sms.getOriginatingAddress();
		String body=sms.getMessageBody();
		
		//���sms����һЩ������Ϣ�����ǿ���ֹͣ���ܹ㲥
		if(body.contains("sb")){
			System.out.println("�յ�һ��������Ϣ��");
			abortBroadcast();//��ֹ���չ㲥
		}else{
			System.out.println("address:"+address+"\n"+"body:"+body);
		}
	}

}
