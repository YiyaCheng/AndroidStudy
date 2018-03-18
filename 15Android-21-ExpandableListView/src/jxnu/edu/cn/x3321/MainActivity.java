package jxnu.edu.cn.x3321;

import jxnu.edu.cn.x3321.adapter.MyExpandableListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	//1.定义成员变量
	ExpandableListView elv;
	String [] grade=new String[]{
		"一年级","二年级","三年级","四年级"	
	};
	
	String [][]classes=new String[][]{
			{"一班","二班","三班"},
			{"一班","二班","三班"},
			{"一班","二班","三班"},
			{"一班","二班","三班"}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.创建ExpandableListAdapter对象封装数据
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
