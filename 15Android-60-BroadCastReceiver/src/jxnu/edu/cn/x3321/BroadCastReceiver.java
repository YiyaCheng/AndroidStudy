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
		Toast.makeText(context, "���ǴӺ�̨service���͵Ĺ㲥��Ϣ"+msg, Toast.LENGTH_SHORT).show();
		//4.��mainfest.xml��Ϊ�㲥�����߶��Ĺ㲥
		
	}

}
