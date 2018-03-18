package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	//1.定义变量
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化 
		inint();
	}

	private void inint() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("菜单项1");
		menu.add("菜单项2");
		menu.add("菜单项3");
		menu.add(0,1,0,"菜单项4");
		menu.add(0,2,0,"菜单项5");
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String result="你选择的是：";
		if(item.getTitle().equals("菜单项1")){
			result=result+item.getTitle();
		}else if(item.getTitle().equals("菜单项2")){
			result=result+item.getTitle();
		}else if(item.getTitle().equals("菜单项3")){
			result=result+item.getTitle();
		}
		
		tv.setText(result);
		return super.onOptionsItemSelected(item);
	}

}
