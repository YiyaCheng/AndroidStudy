package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		for(int i=0;i<9;i++){
			al.add("tom"+i);
		}
		//4.自己定义Adapter封装数据
		MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		//5.把MyAdapter适配器中的数据加载到lv中
		lv.setAdapter(ma);
		//6.定义注册事件监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
						// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						al.get(arg2), Toast.LENGTH_SHORT)
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
