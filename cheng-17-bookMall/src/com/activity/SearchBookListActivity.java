package com.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.Interface.UserInterface;
import com.InterfaceImp.UserInterfaceImp;
import com.example.cheng_17_bookmall.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SearchBookListActivity extends Activity {

	ListView lvSearch;
	Button btSearchBack;
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	UserInterface ui;
	SimpleAdapter sa;
	public static int CLICK_ID=-10000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_book_list);
		init();
		
		//3.从intent中获得要查找的书籍名称
		String name=this.getIntent().getStringExtra("name");
		//4.根据要查找的书籍名称从数据库表取出对应的书籍
		ui=new UserInterfaceImp(getApplicationContext());
		
		list=ui.getUsersByName(name);
		
		sa=new SimpleAdapter(this,list,R.layout.listitem,
				new String[]{"name","author"},
				new int[]{R.id.tvName,R.id.tvAuthor});
		lvSearch.setAdapter(sa);
		
		registerForContextMenu(lvSearch);
		
		btSearchBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SearchBookListActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
	}

	public boolean onContextItemSelected(MenuItem item) {
		//要知道对listview哪条记录进行处理

		//5.获得电话号码
		AdapterContextMenuInfo infor=(AdapterContextMenuInfo)item.getMenuInfo();
		//infor.position表示listview下标为position的记录;
		//String phone=(String)list.get(infor.position).get("author");
		//6.获得被选中的记录的关键字（userid）
		CLICK_ID=(Integer)list.get(infor.position).get("userid");
		
		if(item.getTitle().equals("修改")){
			
			if(CLICK_ID!=-10000){
				Intent intent=new Intent(SearchBookListActivity.this,
						UpdateBookActivity.class);
				intent.putExtra("userid",CLICK_ID);
				startActivity(intent);
				finish();
			}
			
		}else if(item.getTitle().equals("删除")){
			if(CLICK_ID!=-10000){
				AlertDialog ad=creatDialog();//确认是否删除
				ad.show();
			}
		}
		return super.onContextItemSelected(item);
	}

	private AlertDialog creatDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(SearchBookListActivity.this);
		ad.setTitle("删除对话框");
		ad.setIcon(R.id.lv_book);
		ad.setMessage("你读书速度真快呢，确定此书读完了吗？");
		ad.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//实际上就是调用自己重新封装数据
				if(deleteItem()!=0){
					Toast.makeText(getApplicationContext(), "恭喜哦，你离书迷又进了一步呢",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SearchBookListActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "删除不成功",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		ad.setNegativeButton("取消", null);
		dialog=ad.create();
		return dialog;
	}

	protected int deleteItem() {
		// TODO Auto-generated method stub
		int bl=0;
		ui=new UserInterfaceImp(getApplicationContext());
		bl=ui.deleteById(CLICK_ID);
		return bl;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add("修改");
		menu.add("删除");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	private void init() {
		// TODO Auto-generated method stub
		lvSearch=(ListView)this.findViewById(R.id.lv_search);
		btSearchBack=(Button)this.findViewById(R.id.btSearch_back);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_book_list, menu);
		return true;
	}

}
