package com.example.cheng_16_message;

import android.os.Bundle;

import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.*;

public class SendMessageActivity extends Activity {

	ImageView back,addUser;
	GridView grid;
	String []receivers={"°Ö°Ö","ÂèÂè","¸ç¸ç","10086","Í¬Ñ§"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		
		init();
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SendMessageActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
		List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
		for(int i=0;i<receivers.length;i++)
		{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", receivers[i]);
			listItem.add(map);
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItem,
				R.layout.gridview,new String[]{"name"},new int[]{R.id.bt});
		grid.setAdapter(simpleAdapter);
	}

	private void init() {
		// TODO Auto-generated method stub
		back=(ImageView)this.findViewById(R.id.back);
		addUser=(ImageView)this.findViewById(R.id.adduser);
		grid=(GridView)this.findViewById(R.id.gridview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_message, menu);
		return true;
	}

}
