package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MyAdapter extends BaseAdapter {
	Context context;
	ArrayList<String> al;

	public MyAdapter(Context applicationContext, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.al=al;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return al.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//自定义布局管理器格式化每条数据
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(0);
		ll.setPadding(0, 12,0,12);
		
		TextView tvUserName=new TextView(context);
		tvUserName.setText(al.get(position));
		tvUserName.setTextColor(Color.BLUE);
		tvUserName.setPadding(0,6, 6,6);
		
		TextView tvTel=new TextView(context);
		tvTel.setText("1390000000"+position);
		tvTel.setTextColor(Color.RED);
		tvTel.setPadding(0,6, 6,6);
		
		ll.addView(tvUserName);
		ll.addView(tvTel);
		
		return ll;
	}

}
