package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BroadCast extends Service {

	boolean flag=true;
	//1.ΪҪ���͵Ĺ㲥����һ��Ψһ����ݱ�ǣ����֣�
	static final String MSG="jxnu.edu.cn.x3321.Message";
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	/**/

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		
		//ÿ��һ�뷢��һ����Ϣ��ֻ���̲߳�����ͣһ��
		new Thread(){
			public void run(){
				while(flag){
					//2.ÿ��һ�뽫Ҫ�㲥����Ϣͨ��intent�㲥��ȥ
					Intent intent=new Intent(MSG);
					intent.putExtra("msg", "BroadCastMessage");
					sendBroadcast(intent);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//3.��mainfest.xml��ע��service
					
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
