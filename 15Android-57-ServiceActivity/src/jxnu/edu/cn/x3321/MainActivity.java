package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	//1.定义变量
	ProgressBar pb;
	Button btStart;
	DownLoadService ds;
	int progress=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.通过bindService启动服务
		Intent intent=new Intent(MainActivity.this,DownLoadService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);
		
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//5.调用DownLoadService中的downLoad()方法下载数据
				ds.downLoad();
				//6.开始监听
				listenProgress();
				//7.在androidMainfest.xml配置service

			}
		});
	}
	/*
	 * 监听下载进度，每隔1秒钟就去调用ownLoadService中的getProgress()
	 * 获得当前进度值，并更新UI
	 */
	
	protected void listenProgress() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
				while(progress<DownLoadService.MAX_PROGRESS){
					progress=ds.getProgress();
					pb.setProgress(progress);
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

	ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//4.得到正在运行的service对象
			ds=((DownLoadService.DownLoadBinder)service).getService();
		}
	};

	private void init() {
		// TODO Auto-generated method stub
		pb=(ProgressBar)this.findViewById(R.id.pb);
		btStart=(Button)this.findViewById(R.id.btStart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
