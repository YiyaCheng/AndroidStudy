package jxnu.edu.cn.edu.adapter;

import jxnu.edu.cn.edu.MainActivity;
import jxnu.edu.cn.edu.R;
import jxnu.edu.cn.edu.R.layout;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MyExpandAdapter extends BaseExpandableListAdapter {

	Context context;
	String[] group;
	String[][]grouplist;
	int [][]images={{R.drawable.iv1,R.drawable.iv2,R.drawable.iv3},
			{R.drawable.iv4,R.drawable.iv5,R.drawable.iv6},
			{R.drawable.iv7,R.drawable.iv8},
			{R.drawable.iv9,R.drawable.iv10,R.drawable.iv11},
			{R.drawable.iv12,R.drawable.iv13,R.drawable.iv14}};
	String [][]signs;
	public  MyExpandAdapter(Context context,String[] group,String[][] grouplist,String[][]signs)
	{
		this.context=context;
		this.group=group;
		this.grouplist=grouplist;
		this.signs=signs;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return grouplist[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		LinearLayout ll=(LinearLayout)LinearLayout.inflate(context,R.layout.childlayout,null);
		ImageView headImage=(ImageView)ll.findViewById(R.id.headimage);
		headImage.setImageResource(images[groupPosition][childPosition]);
	
		TextView tvName=(TextView)ll.findViewById(R.id.tv_name);
		tvName.setText(grouplist[groupPosition][childPosition]);
		tvName.setPadding(10,10,10,10);
		TextView tvSign=(TextView)ll.findViewById(R.id.tv_sign);
		tvSign.setText(signs[groupPosition][childPosition]);
		tvSign.setPadding(10,10,10,10);
		return ll;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return grouplist[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		//自定义布局管理器格式化每组（一级菜单）数据
		
		/*LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(0);
		ImageView arrow=new ImageView(context);
		
		if (isExpanded) {
            //箭头向下
            arrow.setImageResource(R.drawable.arrow_down);
        }else{//如果组是伸缩状态
            //箭头向右
            arrow.setImageResource(R.drawable.arrow_right);
        }
		
		ll.addView(arrow);
		
		TextView tv=new TextView(context);
		tv.setText(group[groupPosition]);
		tv.setPadding(10,10,10,10);
		tv.setTextColor(Color.BLACK);
        ll.addView(tv);*/
        LinearLayout ll=(LinearLayout)LinearLayout.inflate(context,R.layout.grouplayout,null);
        ImageView arrow=(ImageView)ll.findViewById(R.id.arrow);
        /*if (isExpanded) {
            //箭头向下
            arrow.setImageResource(R.drawable.arrow_down);
        }else{//如果组是伸缩状态
            //箭头向右
            arrow.setImageResource(R.drawable.arrow_right);
        }*/
        
        TextView groupName=(TextView)ll.findViewById(R.id.group_name);
        groupName.setText(group[groupPosition]);
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
