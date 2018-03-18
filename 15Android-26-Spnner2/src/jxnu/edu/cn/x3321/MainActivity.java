package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�����Ա���ñ���
	EditText et;
	Button bt;
	Spinner sp;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3.׼������
		al.add("aaaa");
		//4.�Զ�����������װ����
		ma=new MyAdapter(getApplicationContext(),al);
		//5.��ma�е����ݼ��ص�sp��
		sp.setAdapter(ma);
		//6.���塢ע���¼�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=et.getText().toString().trim();
				if(str==null||str.equals("")){
					Toast.makeText(getApplicationContext(),
							"��������ȷ���ݣ�", Toast.LENGTH_SHORT).show();
				}else{
					AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
					ad.setTitle("��ʾ��Ϣ�Ի���");
					ad.setIcon(R.drawable.ic_launcher);
					ad.setMessage("��ȷ������"+str+"��?");
					ad.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							al.add(et.getText().toString().trim());
							et.setText("");
						}
					});
					ad.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							et.setText("");
						}
					});
					ad.create();
					ad.show();
				}
				
				
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		et=(EditText)this.findViewById(R.id.et);
		bt=(Button)this.findViewById(R.id.bt);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
