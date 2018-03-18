package jxnu.edu.cn.x3321;

import jxnu.edu.cn.x3321.adapter.MyExpandableListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	//1.�����Ա����
	ExpandableListView elv;
	String [] grade=new String[]{
		"һ�꼶","���꼶","���꼶","���꼶"	
	};
	
	String [][]classes=new String[][]{
			{"һ��","����","����"},
			{"һ��","����","����"},
			{"һ��","����","����"},
			{"һ��","����","����"}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3.����ExpandableListAdapter�����װ����
		MyExpandableListAdapter mead=new MyExpandableListAdapter(getApplicationContext()
				,grade,classes);
		
		elv.setAdapter(mead);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		elv=(ExpandableListView)this.findViewById(R.id.elv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
