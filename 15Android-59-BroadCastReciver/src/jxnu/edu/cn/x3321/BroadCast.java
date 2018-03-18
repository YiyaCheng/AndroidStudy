package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BroadCast extends Service {
	boolean flag=true;
	//1.为要发送的广播定义一个身份标记(名字)
	static final String MSG="jxnu.edu.cn.x3321.Message";

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
				while(flag){
					//2.每隔1秒，把要广播的信息通过Inent广播出去
					Intent intent=new Intent(MSG);
					intent.putExtra("msg","BroadCastMessge");
					sendBroadcast(intent);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//3.在mainfest.xml中注册service
				}
			}

			
		}.start();
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		flag=false;
		super.onDestroy();
	}
	
	
	
	

}
