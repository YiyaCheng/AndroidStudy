package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	LinearLayout ll;
	Handler hd=new Handler();
	boolean bl=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		for(int i=0;i<10;i++){
			al.add("tom"+i);
		}
		//4.自己定义Adapter封装数据
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
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
		
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				//判断滚动条是否已经到listView的底部并且刚刚是否加载了数据
				if((lv.getLastVisiblePosition()==al.size()-1)&&!bl){
					bl=true;
					ll.setVisibility(View.VISIBLE);
					new Thread(){
						public void run(){
							try {
								Thread.sleep(3000);
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
									ll.setVisibility(View.INVISIBLE);
									ma.notifyDataSetChanged();
									bl=false;
								}
								
							});
						}
					}.start();
					
				}			
		
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
		ll=(LinearLayout)this.findViewById(R.id.ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
