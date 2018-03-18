package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.定义成员变量
	ListView lv;
	//2.准备数据
	String [] city={
			"南昌","北京","上海",
	        "广州", "郑州","杭州",
	        "武汉","深圳","成都",
	        "重庆","西安"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//3.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//4.把数据封装到arrayAdapter适配器
		ArrayAdapter aa=new ArrayAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1,city);
		//5.把arrayAdapter适配器加载到listView
		lv.setAdapter(aa);
		//6.定义注册事件监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv=(TextView)arg1;
				String str=tv.getText().toString();
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
				.show();
			}
		});;
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
