package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownLoadService extends Service {
	public static final int MAX_PROGRESS=100;//设置进度条最大值100
	private int progress=0; //当前进度条的值，每秒增加5
	/*
	 * 返回下载进度值，供Activity调用
	 */
	public int getProgress(){
		return progress;
	}
	/*
	 * 模拟下载任务，每隔1秒更新前进进度条的值，每秒增加5
	 */
	public void downLoad(){
		new Thread(){
			public void run(){
				while(progress<MAX_PROGRESS){
					progress=progress+5;
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
		return new DownLoadBinder();
	}
	
	public class DownLoadBinder extends Binder{
		public DownLoadService getService(){
			return DownLoadService.this;
		}
	}

}
