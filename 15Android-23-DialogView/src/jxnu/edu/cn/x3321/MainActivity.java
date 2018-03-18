package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�����Ա����
	Button bt;
	EditText etUserName,etPassWord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3����ע���¼�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=creteDialog();
				ad.show();
			}
		});
	}

	protected AlertDialog creteDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("�Զ���View�Ի���");
		ad.setIcon(R.drawable.ic_launcher);
		
		//�����Զ����login.xml��������
		final LinearLayout ll=(LinearLayout)getLayoutInflater()
				.inflate(R.layout.login, null);
		
		//��login.xml���ص�Dialog��
		ad.setView(ll);
		
		ad.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etUserName=(EditText)ll.findViewById(R.id.et_userName);
				etPassWord=(EditText)ll.findViewById(R.id.et_passWord);
				Toast.makeText(getApplicationContext(), "���¼����Ϣ�ͣ�"+"\n"
				+etUserName.getText().toString()+"\n"
				+etPassWord.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		ad.setNegativeButton("ȡ��",null);
		
		alertDialog=ad.create();
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassWord=(EditText)this.findViewById(R.id.et_passWord);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
