package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("===onCreat===");
		super.onCreate();
	}
	
    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
    	System.out.println("===onStarCommand===");
		return super.onStartCommand(intent, flags, startId);
	}
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		System.out.println("===onBind===");
		return null;
	}
    
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("===onUnbind===");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		System.out.println("===onDestroy===");
		super.onDestroy();
	}

	

	

}
