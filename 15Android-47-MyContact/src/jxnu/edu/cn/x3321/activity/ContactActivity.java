package jxnu.edu.cn.x3321.activity;

import java.util.ArrayList;
import java.util.HashMap;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ContactActivity extends Activity {
	//1.定义变量
	ListView lv;
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	UserInterface ui;
	SimpleAdapter sa;
	public static int CLICK_ID=-10000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		//2.初始化
		init();
		//3.从数据库的user表取出所有的联系人
		ui=new UserInterfaceImp(getApplicationContext());
		list=ui.getAllUsers();
		//4.把list数据封装到适配器中
		sa=new SimpleAdapter(this,list,R.layout.listitem,
				new String[]{"name","phone"},
				new int[]{R.id.tvName,R.id.tvPhone});
		lv.setAdapter(sa);
		registerForContextMenu(lv);
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//5.获得电话号码
		AdapterContextMenuInfo infor=
				(AdapterContextMenuInfo)item.getMenuInfo();
		String phone=(String)list.get(infor.position).get("phone");
		//6.获得被选中的记录的关键字(userid)
		CLICK_ID=(Integer)list.get(infor.position).get("userid");
				
		if(item.getTitle().equals("拨号")){
			//通过隐式方式来启动系统打电话的组件
			Intent intent=new Intent();
			intent.setAction("android.intent.action.CALL");
	//		intent.setAction(Intent.ACTION_CALL);
			//把电话号码字符串转化成URI对象，传给系统打电话的组件
			Uri uri=Uri.parse("tel:"+phone);
			intent.setData(uri);
			startActivity(intent);
			//在androidmanifest.xml中配置打电话的权限
			
		}else if(item.getTitle().equals("发送短信")){
			//通过隐式意图来启动系统发送短信的界面
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_SENDTO);
		//	intent.setAction("android.intent.action.SENDTO");
			//将电话号码字符串转换成URI对象，传给系统发送短信的组件
			Uri uri=Uri.parse("smsto:"+phone);
			intent.setData(uri);
			startActivity(intent);
			//在AndroidManifest.xml中配置发送短信的权限	
		}else if(item.getTitle().equals("修改联系人")){
			if(CLICK_ID!=-10000){
				Intent intent=new Intent(ContactActivity.this,
						UpdateContactActivity.class);
				intent.putExtra("userid", CLICK_ID);
				startActivity(intent);
				finish();
			}
		}else if(item.getTitle().equals("删除联系人")){
			if(CLICK_ID!=-10000){
				AlertDialog ad=createDialog();
				ad.show();
			}
		}
		return super.onContextItemSelected(item);
	}


	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder  ad=new AlertDialog.Builder(ContactActivity.this);
		ad.setTitle("删除联系人对话框");
		ad.setIcon(R.drawable.icon);
		ad.setMessage("确定删除吗？");
		ad.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(deleteItem()!=0){
					Toast.makeText(getApplicationContext(),
							"删除联系人成功!", Toast.LENGTH_SHORT)
							.show();
					Intent intent=new Intent(ContactActivity.this,MyContactActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(),
							"删除联系人不成功!", Toast.LENGTH_SHORT)
							.show();
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
		
		menu.add("拨号");
		menu.add("发送短信");
		menu.add("修改联系人");
		menu.add("删除联系人");
		super.onCreateContextMenu(menu, v, menuInfo);
	}



	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
