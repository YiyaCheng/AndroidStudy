package com.example.test__broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadBroadCast extends Service {

	public static final int MAX_PROGRESS=100;//设置进度条最大值100
	private int progress=0; //当前进度条的值，每秒增加5
	
	//1.为要发送的广播定义一个唯一的身份标记（名字）
	static final String MSG="jxnu.edu.cn.x3321.Progress";
	
	/*
	 * 模拟下载任务，每隔1秒更新前进进度条的值，每秒增加5
	 */
	public void downLoad(){
		new Thread(){
			public void run(){
				while(progress<MAX_PROGRESS){
					progress=progress+5;
					//发送广播，把progress广播给订阅者
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
