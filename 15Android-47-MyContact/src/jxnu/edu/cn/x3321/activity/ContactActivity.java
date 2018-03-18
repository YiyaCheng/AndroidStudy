package jxnu.edu.cn.x3321.activity;

import java.util.ArrayList;
import java.util.HashMap;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ContactActivity extends Activity {
	//1.�������
	ListView lv;
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	UserInterface ui;
	SimpleAdapter sa;
	public static int CLICK_ID=-10000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		//2.��ʼ��
		init();
		//3.�����ݿ��user��ȡ�����е���ϵ��
		ui=new UserInterfaceImp(getApplicationContext());
		list=ui.getAllUsers();
		//4.��list���ݷ�װ����������
		sa=new SimpleAdapter(this,list,R.layout.listitem,
				new String[]{"name","phone"},
				new int[]{R.id.tvName,R.id.tvPhone});
		lv.setAdapter(sa);
		registerForContextMenu(lv);
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//5.��õ绰����
		AdapterContextMenuInfo infor=
				(AdapterContextMenuInfo)item.getMenuInfo();
		String phone=(String)list.get(infor.position).get("phone");
		//6.��ñ�ѡ�еļ�¼�Ĺؼ���(userid)
		CLICK_ID=(Integer)list.get(infor.position).get("userid");
				
		if(item.getTitle().equals("����")){
			//ͨ����ʽ��ʽ������ϵͳ��绰�����
			Intent intent=new Intent();
			intent.setAction("android.intent.action.CALL");
	//		intent.setAction(Intent.ACTION_CALL);
			//�ѵ绰�����ַ���ת����URI���󣬴���ϵͳ��绰�����
			Uri uri=Uri.parse("tel:"+phone);
			intent.setData(uri);
			startActivity(intent);
			//��androidmanifest.xml�����ô�绰��Ȩ��
			
		}else if(item.getTitle().equals("���Ͷ���")){
			//ͨ����ʽ��ͼ������ϵͳ���Ͷ��ŵĽ���
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_SENDTO);
		//	intent.setAction("android.intent.action.SENDTO");
			//���绰�����ַ���ת����URI���󣬴���ϵͳ���Ͷ��ŵ����
			Uri uri=Uri.parse("smsto:"+phone);
			intent.setData(uri);
			startActivity(intent);
			//��AndroidManifest.xml�����÷��Ͷ��ŵ�Ȩ��	
		}else if(item.getTitle().equals("�޸���ϵ��")){
			if(CLICK_ID!=-10000){
				Intent intent=new Intent(ContactActivity.this,
						UpdateContactActivity.class);
				intent.putExtra("userid", CLICK_ID);
				startActivity(intent);
				finish();
			}
		}else if(item.getTitle().equals("ɾ����ϵ��")){
			if(CLICK_ID!=-10000){
				AlertDialog ad=createDialog();
				ad.show();
			}
		}
		return super.onContextItemSelected(item);
	}


	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder  ad=new AlertDialog.Builder(ContactActivity.this);
		ad.setTitle("ɾ����ϵ�˶Ի���");
		ad.setIcon(R.drawable.icon);
		ad.setMessage("ȷ��ɾ����");
		ad.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(deleteItem()!=0){
					Toast.makeText(getApplicationContext(),
							"ɾ����ϵ�˳ɹ�!", Toast.LENGTH_SHORT)
							.show();
					Intent intent=new Intent(ContactActivity.this,MyContactActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(),
							"ɾ����ϵ�˲��ɹ�!", Toast.LENGTH_SHORT)
							.show();
				}
				
			}
		});
		ad.setNegativeButton("ȡ��", null);
		dialog=ad.create();
		return dialog;
	}


	protected int deleteItem() {
		// TODO Auto-generated method stub
		int bl=0;
		ui=new UserInterfaceImp(getApplicationContext());
		bl=ui.deleteById(CLICK_ID);
		return bl;
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
		menu.add("����");
		menu.add("���Ͷ���");
		menu.add("�޸���ϵ��");
		menu.add("ɾ����ϵ��");
		super.onCreateContextMenu(menu, v, menuInfo);
	}



	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
