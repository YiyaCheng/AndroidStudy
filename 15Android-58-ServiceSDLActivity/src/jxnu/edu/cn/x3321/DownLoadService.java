package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownLoadService extends Service {

	public static final int MAX_PROGRESS=100;//���ý��������ֵ100
	private int progress=0; //��ǰ��������ֵ��ÿ������5
	private OnProgressListener onProgressListener;//���½��ȵĻص��ӿ�
	
	/*
	 * ע��ص��ӿڵķ��������ⲿ����
	 */
	public void setOnProgressListener(OnProgressListener onProgressListener) {
		this.onProgressListener = onProgressListener;
	}
	
	/*
	 * �������ؽ���ֵ,��Activity����
	 */
	public int getProgress(){
		return progress;
	}
	
	
	/*
	 * ģ����������ÿ��1�����ǰ��������ֵ��ÿ������5
	 */
	public void downLoad(){
		new Thread(){
			public void run(){
				while(progress<MAX_PROGRESS){
					progress=progress+5;
					//���ȷ����ı�֪ͨ������
					if(onProgressListener!=null){
						onProgressListener.onProgress(progress);
					}
					
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
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new DownLoadServiceBinder();
	}
	public class DownLoadServiceBinder extends Binder{
		public DownLoadService getDownLoadService(){
			return DownLoadService.this;
		}
	}

}
