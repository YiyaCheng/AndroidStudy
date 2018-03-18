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
	//�������
	EditText etNumber,etBody;
	Button btSend,btOpen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		inint();
		//3.����ע���¼�������
		btSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��õ绰�����Լ��ʼ�����
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				if(toNumber==null||toNumber.equals("")){
					Toast.makeText(getApplicationContext(),
							"������绰����!", Toast.LENGTH_SHORT)
							.show();
				}else{
					//SmsManager����ֱ�ӷ��Ͷ���
					SmsManager sm=SmsManager.getDefault();
					List<String> sms=sm.divideMessage(body);
					for(String m:sms){
						sm.sendTextMessage(toNumber, null, 
								m, null, null);
					}
					//��AndroidManifest.xml�����÷��Ͷ��ŵ�Ȩ��					
				}
			}
		});
		btOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��õ绰�����Լ��ʼ�����
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				if(toNumber==null||toNumber.equals("")){
					Toast.makeText(getApplicationContext(),
							"������绰����!", Toast.LENGTH_SHORT)
							.show();
				}else{
					//ͨ����ʽ��ͼ������ϵͳ���Ͷ��ŵĽ���
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_SENDTO);
				//	intent.setAction("android.intent.action.SENDTO");
					//���绰�����ַ���ת����URI���󣬴���ϵͳ���Ͷ��ŵ����
					Uri uri=Uri.parse("smsto:"+toNumber);
					intent.setData(uri);
					intent.putExtra("sms_body",body);
					startActivity(intent);
					//��AndroidManifest.xml�����÷��Ͷ��ŵ�Ȩ��	
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
