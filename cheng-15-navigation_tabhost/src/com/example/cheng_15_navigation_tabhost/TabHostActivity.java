package com.example.cheng_15_navigation_tabhost;

import android.os.Bundle;

import java.util.ArrayList;

import com.example.cheng_15_navigation_tabhost.adapter.list1Adapter;
import com.example.cheng_15_navigation_tabhost.adapter.list2Adapter;
import com.example.cheng_15_navigation_tabhost.adapter.addAdapter;

import android.app.Activity;
import android.app.TabActivity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TabHost.TabSpec;

public class TabHostActivity extends TabActivity {

	TabHost tabHost;
	TabWidget tabWidget;
	LinearLayout ll;
	ListView lv1;
	ListView lv2;
	ListView lv3;
	Button bt,btRegit;
	int[]images={R.drawable.g1,R.drawable.g2,R.drawable.g3,
			R.drawable.g4,R.drawable.g5};
	String[] textTitle={"Ipad","ThinkPad X230i",
			"笔记本双肩包","艾美特落地扇FSW52R","吸尘器D-9"};
	String[] prices={"￥3499","￥4299","￥149","￥199","￥398"};
	String[] textContent={
			"电池容量大，使用A5X双核处理器，四核GPU",
			"ThinkPad X230i(2306-6FC) 12.5英寸笔记本",
			"ThinkPad原装Radiant典雅型笔记本双肩背包78Y23790",
			"领跑京东风扇销售！万人见证品质保障！超长定时 全能遥控 超高性价比！",
			"全网销量冠军，吸尘器类目唯一近25000条好评！1800W强劲功率，性价比之王！更有多款优惠套装！"};
	AutoCompleteTextView ac;
	ArrayList<Goods> goods=new ArrayList<Goods>();
	ArrayList<Goods> shoppinggoods=new ArrayList<Goods>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_tab_host);
		init();
		for(int i=0;i<images.length;i++)
		{
			goods.add(new Goods(images[i],textTitle[i],textContent[i],prices[i]));
		}
		
	    list1Adapter la=new list1Adapter(getApplicationContext(),goods);
		lv1.setAdapter(la);
		lv1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				shoppinggoods.add(new Goods(images[arg2],textTitle[arg2],textContent[arg2],prices[arg2]));
				addAdapter la3=new addAdapter(getApplicationContext(),shoppinggoods);
			    lv3.setAdapter(la3);
				Toast.makeText(getApplication(), "该商品已添加到购物车", Toast.LENGTH_LONG).show();
				return false;
			}
		});
		
		tabHost=getTabHost();
		
		
		TabSpec tab1=tabHost.newTabSpec("tab1");
		tab1.setIndicator("商品列表");
		tab1.setContent(R.id.ltab1);
		
		ArrayAdapter<String> aa=new ArrayAdapter<String>(
				getApplicationContext(),R.layout.autotextview,R.id.autoComplete
				,textTitle);
		ac.setAdapter(aa);
		TabSpec tab2=tabHost.newTabSpec("tab2");
		tab2.setIndicator("查找商品");
		tab2.setContent(R.id.ltab2);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=ac.getText().toString().trim();
				for(int i=0;i<images.length;i++)
				if(str.equals(textTitle[i]))
				{
					list2Adapter la2=new list2Adapter(getApplicationContext(),i,ll,goods);
					lv2.setAdapter(la2);
					ac.setText("");
				}
			}
		});
		
		TabSpec tab3=tabHost.newTabSpec("tab3");
		tab3.setIndicator("购物车");
		tab3.setContent(R.id.ltab3);
		lv3.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub

				shoppinggoods.remove(arg2);
				addAdapter la3=new addAdapter(getApplicationContext(),shoppinggoods);
				lv3.setAdapter(la3);
				
				Toast.makeText(getApplication(), "该商品已从购物车移除", Toast.LENGTH_LONG).show();
				return true;
			}
		});
		
		TabSpec tab4=tabHost.newTabSpec("tab4");
		tab4.setIndicator("登陆");
		tab4.setContent(R.id.ltab4);
		btRegit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "您已成功登陆！", Toast.LENGTH_SHORT).show();
			}
		});
	
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
		tabWidget=tabHost.getTabWidget();
		for(int i=0;i<tabWidget.getChildCount();i++)
		{
			TextView tv=(TextView)tabWidget.getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(Color.WHITE);
		}
		
	}

    private void init() {
		// TODO Auto-generated method stub
    	ll=(LinearLayout)this.findViewById(R.id.linear);
		lv1=(ListView)this.findViewById(R.id.listview1);
		lv2=(ListView)this.findViewById(R.id.listview2);
		lv3=(ListView)this.findViewById(R.id.listview3);
		ac=(AutoCompleteTextView)this.findViewById(R.id.ac);
		bt=(Button)this.findViewById(R.id.searchImage);
		btRegit=(Button)this.findViewById(R.id.regit);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
