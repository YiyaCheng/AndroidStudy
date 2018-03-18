package jxnu.edu.cn.x3321.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	String [] grade;
	String [][]classes;
	Context context;
	public MyExpandableListAdapter(Context applicationContext, String[] grade,
			String[][] classes) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.grade=grade;
		this.classes=classes;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return classes[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		TextView tv=new TextView(context);
		tv.setText(classes[groupPosition][childPosition]);
		tv.setTextColor(Color.BLUE);
		tv.setPadding(0,6, 6,6);
		return tv;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return classes[groupPosition].length;
	}
	
/////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return grade[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return grade.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//自定义布局管理器格式化每组（一级菜单）数据
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(0);
		ll.setPadding(0, 12,0,12);
		
		TextView tv=new TextView(context);
		tv.setText(grade[groupPosition]);
		tv.setTextColor(Color.BLUE);
		tv.setPadding(0,6, 6,6);
		
		ll.addView(tv);
		
		return ll;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
