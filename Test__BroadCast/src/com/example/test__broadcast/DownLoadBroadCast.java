package com.example.test__broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadBroadCast extends Service {

	public static final int MAX_PROGRESS=100;//���ý��������ֵ100
	private int progress=0; //��ǰ��������ֵ��ÿ������5
	
	//1.ΪҪ���͵Ĺ㲥����һ��Ψһ����ݱ�ǣ����֣�
	static final String MSG="jxnu.edu.cn.x3321.Progress";
	
	/*
	 * ģ����������ÿ��1�����ǰ����������ֵ��ÿ������5
	 */
	public void downLoad(){
		new Thread(){
			public void run(){
				while(progress<MAX_PROGRESS){
					progress=progress+5;
					//���͹㲥����progress�㲥��������
					Intent intent=new Intent(MSG);
					intent.putExtra("msg", progress);
					sendBroadcast(intent);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		downLoad();
		return super.onStartCommand(intent, flags, startId);
	}

	

}
