package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

import com.Interface.UserInterface;
import com.InterfaceImp.UserInterfaceImp;
import com.bean.User;
import com.example.cheng_17_bookmall.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class SearchBookActivity extends Activity {

	//EditText etSearch;
	//ImageButton ibSearch;
	AutoCompleteTextView ac;
	Button btSearch;
	ArrayList<String> names=new ArrayList<String>();
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	UserInterface ui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_book);
		
		init();
		
        ui=new UserInterfaceImp(getApplicationContext());
		
		list=ui.getAllUsers();
		for(int j=0;j<list.size();j++)
		{
			//动态添加图书列表的元素到集合中，使用数组初始化时长度就已经固定无法更改，会报错
			names.add(list.get(j).get("name").toString().trim());
		}
		ArrayAdapter<String> aa=new ArrayAdapter<String>(
   				getApplicationContext(),R.layout.auto_down,R.id.autoComplete
   				,names);
   		ac.setAdapter(aa);
   		//使用AutoCompleteTextView进行自动匹配搜索
        /*btSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=ac.getText().toString().trim();
				
				if(name==null||name.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入书籍名称",Toast.LENGTH_LONG).show();
				}else{
					Intent intent=new Intent(SearchBookActivity.this,
							SearchBookListActivity.class);
					intent.putExtra("name", name);
					startActivity(intent);
				}
			}
		});*/
		//普通得edittext的搜索
		/*ibSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=etSearch.getText().toString().trim();
				
				if(name==null||name.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入书籍名称",Toast.LENGTH_LONG).show();
				}else{
					Intent intent=new Intent(SearchBookActivity.this,
							SearchBookListActivity.class);
					intent.putExtra("name", name);
					startActivity(intent);
				}
			}
		});*/
		
		
		
		btSearch.setOnClickListener(new Search());
	}
	/*private void init() {
		// TODO Auto-generated method stub
		etSearch=(EditText)this.findViewById(R.id.et_search);
		ibSearch=(ImageButton)this.findViewById(R.id.ib_search);
	}*/
	
	private void init() {
		// TODO Auto-generated method stub
		ac=(AutoCompleteTextView)this.findViewById(R.id.ac);
		btSearch=(Button)this.findViewById(R.id.searchImage);
	}
	public class Search implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String name=ac.getText().toString().trim();
			
			if(name==null||name.equals("")){
				Toast.makeText(getApplicationContext(),
						"请输入书籍名称",Toast.LENGTH_LONG).show();
			}else{
				Intent intent=new Intent(SearchBookActivity.this,
						SearchBookListActivity.class);
				intent.putExtra("name", name);
				startActivity(intent);
			}
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_book, menu);
		return true;
	}

}
