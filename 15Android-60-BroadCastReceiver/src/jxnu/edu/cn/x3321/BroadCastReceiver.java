package jxnu.edu.cn.x3321;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg=intent.getExtras().getString("msg");
		Toast.makeText(context, "这是从后台service发送的广播消息"+msg, Toast.LENGTH_SHORT).show();
		//4.在mainfest.xml中为广播接收者订阅广播
		
	}

}
