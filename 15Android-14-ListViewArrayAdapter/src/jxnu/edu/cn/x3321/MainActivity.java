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
	//1.�����Ա����
	ListView lv;
	//2.׼������
	String [] city={
			"�ϲ�","����","�Ϻ�",
	        "����", "֣��","����",
	        "�人","����","�ɶ�",
	        "����","����"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//3.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//4.�����ݷ�װ��arrayAdapter������
		ArrayAdapter aa=new ArrayAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1,city);
		//5.��arrayAdapter���������ص�listView
		lv.setAdapter(aa);
		//6.����ע���¼�������
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
