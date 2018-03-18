package com.example.cheng_16_txtreader;

import java.io.File;
import java.io.FileInputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

	Button btTitle;
	TextView tvContent;
	SharedPreferences sp;
	RadioGroup rg1,rg2;
	RadioButton rbDay,rbNight,rbSmall,rbMiddle,rbBig;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		registerForContextMenu(btTitle);
		
		sp=this.getSharedPreferences("setting", Context.MODE_PRIVATE);
		
		boolean isDay=sp.getBoolean("isDay", false);
		boolean isNight=sp.getBoolean("isNight", false);
		boolean isSmall=sp.getBoolean("isSmall",false);
		boolean isMiddle=sp.getBoolean("isMiddle", false);
		boolean isBig=sp.getBoolean("isBig", false);
		
		
		if(isDay)
		{
			tvContent.setBackgroundColor(Color.parseColor("#FFFFE0"));
		}else if(isNight){
			tvContent.setBackgroundColor(Color.parseColor("#636363"));
		}else{
			tvContent.setBackgroundColor(Color.WHITE);
		}
		if(isSmall)
			tvContent.setTextSize(10);
		if(isMiddle)
			tvContent.setTextSize(15);
		if(isBig)
			tvContent.setTextSize(20);
			
		tvContent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				ad.show();
			}
		});
	}

	

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setIcon(R.drawable.ic_launcher);
		
		//加载自定义的setting_layout.xml到程序中
		LinearLayout ll=(LinearLayout)getLayoutInflater()
				.inflate(R.layout.setting_layout, null);
		
		ad.setView(ll);
		rg1=(RadioGroup)ll.findViewById(R.id.rg1);
		rg2=(RadioGroup)ll.findViewById(R.id.rg2);

		rbDay=(RadioButton)ll.findViewById(R.id.day);
		rbNight=(RadioButton)ll.findViewById(R.id.night);
		rbSmall=(RadioButton)ll.findViewById(R.id.small);
		rbMiddle=(RadioButton)ll.findViewById(R.id.middle);
		rbBig=(RadioButton)ll.findViewById(R.id.big);
		
		
		rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Editor edit=sp.edit();
				edit.putBoolean("isDay", false);
				edit.putBoolean("isNight", false);
				if(checkedId==rbDay.getId()){
					tvContent.setBackgroundColor(Color.parseColor("#FFFFE0"));
					edit.putBoolean("isDay", true);
				}
				
				if(checkedId==rbNight.getId()){
					tvContent.setBackgroundColor(Color.parseColor("#636363"));
					edit.putBoolean("isNight", true);
				}
				edit.commit();
			}
		});
		
		rg2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Editor edit=sp.edit();
				edit.putBoolean("isSmall", false);
				edit.putBoolean("isMiddle", false);
				edit.putBoolean("isBig", false);
				if(checkedId==rbSmall.getId()){
					tvContent.setTextSize(10);
					edit.putBoolean("isSmall", true);
				}
				if(checkedId==rbMiddle.getId()){
					tvContent.setTextSize(15);
					edit.putBoolean("isMiddle", true);
				}
				if(checkedId==rbBig.getId()){
					tvContent.setTextSize(20);
					edit.putBoolean("isBig", true);
				}
				edit.commit();
			}
		});
		
		
		ad.setPositiveButton("OK", null); 
		alertDialog=ad.create();
		return alertDialog;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//长按显示的菜单
			menu.add(0,1,0,"one.txt");
			menu.add(0,2,0,"two.txt");
			menu.add(0,3,0,"three.txt");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case 1:
		case 2:
		case 3:
			showContent((String) item.getTitle());
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void showContent(String title) {
		// TODO Auto-generated method stub
		String fileName=title.toString().trim();
		if(fileName==null||fileName.equals("")){
			Toast.makeText(getApplicationContext(),
					"请输入文件名!",Toast.LENGTH_SHORT)
					.show();
		}else{
			//5.在androidManifest.xml增加访问SDCard权限
			//6.检查SDCard是否可用
			if(Environment.getExternalStorageState()
					.equals(Environment.MEDIA_MOUNTED)){
				File file=new File(Environment.getExternalStorageDirectory()
						,fileName);
				//7. 通过FileInputStream 对象把数据从手机的SDCard读到程序
				try {
					FileInputStream fin=new FileInputStream(file);
					byte[] bt=new byte[fin.available()];
					fin.read(bt);
					tvContent.setText(new String(bt));	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(),
							"小说加载失败!",Toast.LENGTH_SHORT)
							.show();
					e.printStackTrace();
				}
				
			}else{
				Toast.makeText(getApplicationContext(),
						"SDCard不存在!",Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	

	private void init() {
		// TODO Auto-generated method stub
		btTitle=(Button)this.findViewById(R.id.title);
		tvContent=(TextView)this.findViewById(R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
