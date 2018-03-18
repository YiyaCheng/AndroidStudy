package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends Activity {
	//1.�������
	TextView tv;
	String []hobbys=new String[]{"��Ӿ","������","дjava����"};
	boolean []bl=new boolean[]{false,false,false};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		//3.�����Ӳ˵�
		SubMenu subMenu=menu.addSubMenu("�Ա�");
		subMenu.add(0,1,0,"��");
		subMenu.add(0,2,0,"Ů");
		subMenu.setGroupCheckable(0, true,true);
		
		menu.add("����");
		
		
		MenuItem exit=menu.add("�˳�");
		exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//4.��Ӧ�û�����Ĳ˵���
		String result="���ѡ���ǣ�";
		if(item.getTitle().equals("��")){
			result=result+"��...";
		}else if(item.getTitle().equals("Ů")){
			result=result+"Ů...";
		}else if(item.getTitle().equals("����")){
			AlertDialog dialog=createDialog();
			dialog.show();
		}
		
		tv.setText(result);
		return super.onOptionsItemSelected(item);
	}

	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog=null;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("����ѡ��Ի���");
		ad.setIcon(R.drawable.ic_launcher);
		
		ad.setMultiChoiceItems(hobbys, bl, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				String result="";
				bl[which]=isChecked;
				for(int i=0;i<bl.length;i++){
					if(bl[i]){
						result=result+"  "+hobbys[i];
					}
				}
				if(result.length()>0){
					tv.setText(result);
				}else{
					tv.setText("");
				}
			}
		});
		
		ad.setPositiveButton("ȷ��", null);
		ad.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText("");
			}
		});
		
		dialog=ad.create();
		return dialog;
	}

}
