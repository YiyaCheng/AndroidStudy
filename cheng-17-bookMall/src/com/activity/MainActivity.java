package com.activity;

import android.os.Bundle;

import com.example.cheng_17_bookmall.R;
import android.app.*;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
    //1.�������
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.������һ��Tabҳ
		tabHost=getTabHost();
		TabSpec tab1=tabHost.newTabSpec("bookList");
		View bookListView=getView(R.drawable.mybook,"ͼ���б�");
		tab1.setIndicator(bookListView);
		Intent intent1=new Intent(MainActivity.this,BookListActivity.class);
		tab1.setContent(intent1);
		tabHost.addTab(tab1);
		
		//3.�����ڶ���Tabҳ
		TabSpec tab2=tabHost.newTabSpec("addBook");
		View addBookView=getView(R.drawable.add,"���ͼ��");
		tab2.setIndicator(addBookView);
		Intent intent2=new Intent(MainActivity.this,AddBookActivity.class);
		tab2.setContent(intent2);
		tabHost.addTab(tab2);
		
		//4.����������Tabҳ
		TabSpec tab3=tabHost.newTabSpec("searchBook");
		View searchBookView=getView(R.drawable.search,"����ͼ��");
		tab3.setIndicator(searchBookView);
		Intent intent3=new Intent(MainActivity.this,SearchBookActivity.class);
		tab3.setContent(intent3);
		tabHost.addTab(tab3);
		
		//5.�������ĸ�Tabҳ
		TabSpec tab4=tabHost.newTabSpec("aboutBook");
		View aboutBookView=getView(R.drawable.about,"����...");
		tab4.setIndicator(aboutBookView);
		Intent intent4=new Intent(MainActivity.this,AboutActivity.class);
		tab4.setContent(intent4);
		tabHost.addTab(tab4);
	}

	private View getView(int tabMainNavContact,String string) {
		// TODO Auto-generated method stub
		View view=View.inflate(getApplicationContext(), R.layout.tablayout,null);
		ImageView iv=(ImageView)view.findViewById(R.id.ivIcon);
		iv.setBackgroundResource(tabMainNavContact);
		TextView tv=(TextView)view.findViewById(R.id.tvTitle);
		tv.setText(string);
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
