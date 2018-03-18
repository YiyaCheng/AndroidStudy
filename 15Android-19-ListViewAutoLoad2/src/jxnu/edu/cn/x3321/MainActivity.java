package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	Button bt;
	ProgressDialog pd;
	Handler hd=new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		pd=new ProgressDialog(this);
		pd.setCancelable(false);
		//3.准备数据
		for(int i=0;i<10;i++){
			al.add("tom"+i);
		}
		
		//4.在ListView底部增加一个load more...按钮
		bt=(Button)View.inflate(getApplicationContext(),
				R.layout.button, null);
		lv.addFooterView(bt);
		//5.自己定义Adapter封装数据
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		//6.把MyAdapter适配器中的数据加载到lv中
		lv.setAdapter(ma);
		//7.定义注册事件监听器
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
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pd.show();
				
				new Thread(){
					public void run(){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(int i=0;i<10;i++){
							al.add("mac"+i);
						}
						
						hd.post(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								ma.notifyDataSetChanged();
								pd.dismiss();
							}
							
						});
					}
					
				}.start();
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
