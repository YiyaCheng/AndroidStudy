package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends Activity {
	//1.定义变量
	TextView tv;
	String []hobbys=new String[]{"游泳","打篮球","写java程序"};
	boolean []bl=new boolean[]{false,false,false};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		//3.创建子菜单
		SubMenu subMenu=menu.addSubMenu("性别");
		subMenu.add(0,1,0,"男");
		subMenu.add(0,2,0,"女");
		subMenu.setGroupCheckable(0, true,true);
		
		menu.add("爱好");
		
		
		MenuItem exit=menu.add("退出");
		exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//4.响应用户点击的菜单项
		String result="你的选择是：";
		if(item.getTitle().equals("男")){
			result=result+"男...";
		}else if(item.getTitle().equals("女")){
			result=result+"女...";
		}else if(item.getTitle().equals("爱好")){
			AlertDialog dialog=createDialog();
			dialog.show();
		}
		
		tv.setText(result);
		return super.onOptionsItemSelected(item);
	}

	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog=null;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("爱好选择对话框");
		ad.setIcon(R.drawable.ic_launcher);
		
		ad.setMultiChoiceItems(hobbys, bl, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				String result="";
				bl[which]=isChecked;
				for(int i=0;i<bl.length;i++){
					if(bl[i]){
						result=result+"  "+hobbys[i];
					}
				}
				if(result.length()>0){
					tv.setText(result);
				}else{
					tv.setText("");
				}
			}
		});
		
		ad.setPositiveButton("确定", null);
		ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText("");
			}
		});
		
		dialog=ad.create();
		return dialog;
	}

}
