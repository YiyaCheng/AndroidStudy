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
		
		//3.��intent�л��Ҫ���ҵ��鼮����
		String name=this.getIntent().getStringExtra("name");
		//4.����Ҫ���ҵ��鼮���ƴ����ݿ��ȡ����Ӧ���鼮
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
		//Ҫ֪����listview������¼���д���

		//5.��õ绰����
		AdapterContextMenuInfo infor=(AdapterContextMenuInfo)item.getMenuInfo();
		//infor.position��ʾlistview�±�Ϊposition�ļ�¼;
		//String phone=(String)list.get(infor.position).get("author");
		//6.��ñ�ѡ�еļ�¼�Ĺؼ��֣�userid��
		CLICK_ID=(Integer)list.get(infor.position).get("userid");
		
		if(item.getTitle().equals("�޸�")){
			
			if(CLICK_ID!=-10000){
				Intent intent=new Intent(SearchBookListActivity.this,
						UpdateBookActivity.class);
				intent.putExtra("userid",CLICK_ID);
				startActivity(intent);
				finish();
			}
			
		}else if(item.getTitle().equals("ɾ��")){
			if(CLICK_ID!=-10000){
				AlertDialog ad=creatDialog();//ȷ���Ƿ�ɾ��
				ad.show();
			}
		}
		return super.onContextItemSelected(item);
	}

	private AlertDialog creatDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(SearchBookListActivity.this);
		ad.setTitle("ɾ���Ի���");
		ad.setIcon(R.id.lv_book);
		ad.setMessage("������ٶ�����أ�ȷ�������������");
		ad.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//ʵ���Ͼ��ǵ����Լ����·�װ����
				if(deleteItem()!=0){
					Toast.makeText(getApplicationContext(), "��ϲŶ�����������ֽ���һ����",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SearchBookListActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "ɾ�����ɹ�",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		ad.setNegativeButton("ȡ��", null);
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
		menu.add("�޸�");
		menu.add("ɾ��");
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
