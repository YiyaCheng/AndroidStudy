package jxnu.edu.cn.x3321.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jxnu.edu.cn.x3321.R;

public class ExpandAdapter extends BaseExpandableListAdapter {

	Context context;
	String[]colors;
	int[][]fillColor;
	public ExpandAdapter(Context applicationContext, String[] colors,int[][]fillColor) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.colors=colors;
		this.fillColor=fillColor;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return fillColor[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout llchild=(LinearLayout)LinearLayout.inflate(context,R.layout.child_item,null);
		TextView tvChild=(TextView)llchild.findViewById(R.id.tv_child);
		tvChild.setBackgroundColor(fillColor[groupPosition][childPosition]);
		/*if(tvChild.isClickable())
		{
			LinearLayout main=(LinearLayout)LinearLayout.inflate(context,R.layout.activity_main,null);
			main.setBackgroundColor(fillColor[groupPosition][childPosition]);
			
		}*/
		return llchild;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return fillColor[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return colors[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return colors.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)LinearLayout.inflate(context,R.layout.group_item,null);
		TextView tvGruop=(TextView)ll.findViewById(R.id.tv_group);
		tvGruop.setText(colors[groupPosition]);
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
		return true;
	}
	
}
