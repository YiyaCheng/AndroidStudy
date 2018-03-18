package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	//1.定义变量
	EditText et1,et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.为两个文本框注册上下文菜单,长按将显示上下文菜单
		registerForContextMenu(et1);
		registerForContextMenu(et2);
	}
	
	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		if(v==et1){//长按第一个文本框显示的菜单
			menu.add(0,1,0,"全部复制");
			menu.add(0,2,0,"全部粘贴");
			menu.add(0,3,0,"全部剪贴");
		}else if(v==et2){
			menu.add(1,4,0,"菜单1");
			menu.add(1,5,0,"菜单2");
			menu.add(1,6,0,"菜单3");
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}



	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case 1:
		case 2:
		case 3:
			et1.append("\n"+item.getTitle()+"被按下");
			break;
		case 4:
		case 5:
		case 6:
			et2.append("\n"+item.getTitle()+"被按下");
			break;
		}
		
		return super.onContextItemSelected(item);
	}



	private void init() {
		// TODO Auto-generated method stub
		et1=(EditText)this.findViewById(R.id.et1);
		et2=(EditText)this.findViewById(R.id.et2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
