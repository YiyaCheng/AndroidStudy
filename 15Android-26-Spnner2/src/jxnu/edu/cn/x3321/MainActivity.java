package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义成员引用变量
	EditText et;
	Button bt;
	Spinner sp;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		al.add("aaaa");
		//4.自定义适配器封装数据
		ma=new MyAdapter(getApplicationContext(),al);
		//5.把ma中的数据加载到sp中
		sp.setAdapter(ma);
		//6.定义、注册事件监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=et.getText().toString().trim();
				if(str==null||str.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入正确数据！", Toast.LENGTH_SHORT).show();
				}else{
					AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
					ad.setTitle("提示信息对话框");
					ad.setIcon(R.drawable.ic_launcher);
					ad.setMessage("你确定增加"+str+"吗?");
					ad.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							al.add(et.getText().toString().trim());
							et.setText("");
						}
					});
					ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							et.setText("");
						}
					});
					ad.create();
					ad.show();
				}
				
				
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		et=(EditText)this.findViewById(R.id.et);
		bt=(Button)this.findViewById(R.id.bt);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
