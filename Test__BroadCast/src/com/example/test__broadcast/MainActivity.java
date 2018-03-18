package com.example.test__broadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	static ProgressBar pb;
	Button btStart;
	BroadCastReceiver progressReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		//3.��̬ע�ᣨ���ģ��㲥(������)
		/*progressReceiver=new BroadCastReceiver();
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("jxnu.edu.cn.x3321.Progress");
		registerReceiver(progressReceiver,intentFilter);*/
		
		/*��̬ע��㲥����������mainfest.xml��д���
		 * <receiver 
           android:name="com.example.test__broadcast.MainActivity$BroadCastReceiver">
            <intent-filter >
           		<action android:name="jxnu.edu.cn.x3321.Progress"/>
       		</intent-filter>
   		</receiver>*/
		
		btStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.����service(�㲥)
				Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
				startService(intent);
				}
		});
	}

	@Override
	protected void onDestroy() {
		// ֹͣ�㲥
		Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
		stopService(intent);
		//ע���㲥
		//unregisterReceiver(progressReceiver);
		super.onDestroy();
	}

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
    //�㲥�����ߣ����н���
   public static class BroadCastReceiver extends BroadcastReceiver {//��̬��һ��Ҫstatic����

		@Override
		public void onReceive(Context context, Intent intent) {
			//�ӹ㲥�л�õ�ǰ����ֵ��������UI
			int progress=intent.getIntExtra("msg",0);
			pb.setProgress(progress);
			
			
		}
	}
}
