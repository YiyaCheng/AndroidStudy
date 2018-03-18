package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	ProgressBar pb;
	Button btStart;
	DownLoadService ds;
	int progress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		//3.ͨ��bindService��������
		Intent intent=new Intent(MainActivity.this,DownLoadService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//5.����DownLoadService�е�downLoad()������������
				ds.downLoad();
				
				//6.��androidMainfest.xml����service
			}
		});
	}

	
	ServiceConnection conn =new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//�õ�DownLoadService����
			ds=((DownLoadService.DownLoadServiceBinder)service).getDownLoadService();
			//ע��ص��ӿ������ؽ��ȵı仯
			ds.setOnProgressListener(new OnProgressListener(){

				@Override
				public void onProgress(int progress) {
					// TODO Auto-generated method stub
					pb.setProgress(progress);
				}
				
			});
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
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
