package jxnu.edu.cn.x3321;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.�����Ա����
	ListView lv;
	ArrayList<Map<String,String>> list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3.׼������
		list=new ArrayList<Map<String,String>>();
		
		Map<String,String> m1=new HashMap<String,String>();
		m1.put("name","����" );
		m1.put("tel","13900000001");
		m1.put("qq","123456");
		list.add(m1);
		
		Map<String,String> m2=new HashMap<String,String>();
		m2.put("name","����" );
		m2.put("tel","13900000002");
		m2.put("qq","123456");
		list.add(m2);
		
		Map<String,String> m3=new HashMap<String,String>();
		m3.put("name","����" );
		m3.put("tel","13900000003");
		m3.put("qq","123456");
		list.add(m3);
		
		Map<String,String> m4=new HashMap<String,String>();
		m4.put("name","����" );
		m4.put("tel","13900000004");
		m4.put("qq","123456");
		list.add(m4);
		
		//4.�����ݷ�װ��SimpleAdapter������
		SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),
				list,R.layout.table,new String[]{"name","tel"},
				new int[]{R.id.tv_userName,R.id.tv_tel});
		//5.��SimpleAdapter���������ص�listView
		lv.setAdapter(sa);
		//6.����ע���¼�������
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						list.get(arg2).get("name")+"\n"
						+list.get(arg2).get("tel")+"\n"
						+list.get(arg2).get("qq"), Toast.LENGTH_SHORT)
						.show();
			}
			
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
