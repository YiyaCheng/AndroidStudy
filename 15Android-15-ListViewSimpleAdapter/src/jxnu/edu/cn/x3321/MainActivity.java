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
	//1.定义成员变量
	ListView lv;
	ArrayList<Map<String,String>> list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		list=new ArrayList<Map<String,String>>();
		
		Map<String,String> m1=new HashMap<String,String>();
		m1.put("name","张三" );
		m1.put("tel","13900000001");
		m1.put("qq","123456");
		list.add(m1);
		
		Map<String,String> m2=new HashMap<String,String>();
		m2.put("name","李四" );
		m2.put("tel","13900000002");
		m2.put("qq","123456");
		list.add(m2);
		
		Map<String,String> m3=new HashMap<String,String>();
		m3.put("name","王五" );
		m3.put("tel","13900000003");
		m3.put("qq","123456");
		list.add(m3);
		
		Map<String,String> m4=new HashMap<String,String>();
		m4.put("name","刘六" );
		m4.put("tel","13900000004");
		m4.put("qq","123456");
		list.add(m4);
		
		//4.把数据封装到SimpleAdapter适配器
		SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),
				list,R.layout.table,new String[]{"name","tel"},
				new int[]{R.id.tv_userName,R.id.tv_tel});
		//5.把SimpleAdapter适配器加载到listView
		lv.setAdapter(sa);
		//6.定义注册事件监听器
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
