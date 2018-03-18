package com.example.cheng_16_message;

import android.os.Bundle;

import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	int images=R.drawable.b;
	String []userName={"发件人1","发件人2"};
	String []times={"09/24","10/14"};
	String []details={"你好吗","我很好"};
	TextView tvEdit,tvSendMessage;
	EditText etSearch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
		for(int i=0;i<userName.length;i++)
		{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("head",images);
			map.put("name",userName[i]);
			map.put("time",times[i]);
			map.put("detail",details[i]);
			listItem.add(map);
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItem,R.layout.list_message,
				new String[]{"head","name","time","detail"},new int[]{R.id.head,R.id.name,R.id.time,R.id.msContent});
		ListView list=(ListView)findViewById(R.id.list1);
		list.setAdapter(simpleAdapter);
		
		tvSendMessage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SendMessageActivity.class);
				startActivity(intent);
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		tvEdit=(TextView)this.findViewById(R.id.tv_edit);
		tvSendMessage=(TextView)this.findViewById(R.id.tv_sendMessage);
		etSearch=(EditText)this.findViewById(R.id.et_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
