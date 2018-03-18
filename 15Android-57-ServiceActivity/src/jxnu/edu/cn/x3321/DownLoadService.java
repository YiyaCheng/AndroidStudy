package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownLoadService extends Service {
	public static final int MAX_PROGRESS=100;//���ý��������ֵ100
	private int progress=0; //��ǰ��������ֵ��ÿ������5
	/*
	 * �������ؽ���ֵ����Activity����
	 */
	public int getProgress(){
		return progress;
	}
	/*
	 * ģ����������ÿ��1�����ǰ����������ֵ��ÿ������5
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
