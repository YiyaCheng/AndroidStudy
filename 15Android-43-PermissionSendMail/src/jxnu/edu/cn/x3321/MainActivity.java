package jxnu.edu.cn.x3321;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//定义变量
	EditText etNumber,etBody;
	Button btSend,btOpen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		inint();
		//3.定义注册事件监听器
		btSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获得电话号码以及邮件内容
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				if(toNumber==null||toNumber.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入电话号码!", Toast.LENGTH_SHORT)
							.show();
				}else{
					//SmsManager对象直接发送短信
					SmsManager sm=SmsManager.getDefault();
					List<String> sms=sm.divideMessage(body);
					for(String m:sms){
						sm.sendTextMessage(toNumber, null, 
								m, null, null);
					}
					//在AndroidManifest.xml中配置发送短信的权限					
				}
			}
		});
		btOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获得电话号码以及邮件内容
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				if(toNumber==null||toNumber.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入电话号码!", Toast.LENGTH_SHORT)
							.show();
				}else{
					//通过隐式意图来启动系统发送短信的界面
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_SENDTO);
				//	intent.setAction("android.intent.action.SENDTO");
					//将电话号码字符串转换成URI对象，传给系统发送短信的组件
					Uri uri=Uri.parse("smsto:"+toNumber);
					intent.setData(uri);
					intent.putExtra("sms_body",body);
					startActivity(intent);
					//在AndroidManifest.xml中配置发送短信的权限	
				}
			}
		});
	}

	private void inint() {
		// TODO Auto-generated method stub
		etNumber=(EditText)this.findViewById(R.id.et_Number);
		etBody=(EditText)this.findViewById(R.id.et_body);
		btSend=(Button)this.findViewById(R.id.bt_send);
		btOpen=(Button)this.findViewById(R.id.bt_Open);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
