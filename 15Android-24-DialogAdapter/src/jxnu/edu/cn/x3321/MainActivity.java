package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	//1.定义成员变量
	Button bt;
	ArrayList<String>  al=new ArrayList<String>();
	MyAdapter ma;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		for(int i=0;i<10;i++){
			al.add("mac"+i);
		}
		//4.自定义适配器封装数据
		ma=new MyAdapter(getApplicationContext(),al);
		//5注册事件监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				AlertDialog ad=creteDialog();
				ad.show();
			}
		});
	}

	protected AlertDialog creteDialog() {
		// TODO Auto-generated method stub
		AlertDialog  alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("自定义数据列表对话框");
		ad.setIcon(R.drawable.ic_launcher);
		//把自定义适配器中的数据加载到Diaog中
		ad.setAdapter(ma, null);
		
		ad.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//自己代码
			}
		});
		ad.setNegativeButton("取消", null);
		
		alertDialog=ad.create();
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
