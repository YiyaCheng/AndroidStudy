package com.example.walk01;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import java.util.*;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements  OnItemClickListener{

	DrawerLayout drawerlayout;
	LinearLayout ll;
	ListView list_left;
	ArrayList <setting>set=new ArrayList<setting>();
	List<Map<String,Object>> setItem=new ArrayList<Map<String,Object>>();
	int[]images={R.drawable.set1,R.drawable.set1,R.drawable.set1};
	String[] textContent={"我的消息","我的收藏","我的文件"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		for(int i=0;i<images.length;i++){
			set.add(new setting(images[i],textContent[i]));
		}
		for(setting s:set){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("image",s.getImageId());
			map.put("content",s.getSetContent());
			setItem.add(map);
		}
		SimpleAdapter aa=new SimpleAdapter(getApplicationContext(),setItem,
				R.layout.layout_leftmenu,
				new String[]{"image","content"},
				new int[]{R.id.iv,R.id.tv_set});
		list_left.setAdapter(aa);
		list_left.setOnItemClickListener(this);
	}

	private void init() {
		// TODO Auto-generated method stub
		drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		list_left=(ListView)findViewById(R.id.list_left_drawer);
		ll=(LinearLayout)findViewById(R.id.ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", set.get(position).getSetContent());
        contentFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.ly_content,contentFragment).commit();
        drawerlayout.closeDrawer(ll);
    }

}
