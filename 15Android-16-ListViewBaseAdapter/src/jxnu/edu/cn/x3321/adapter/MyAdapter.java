package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	ArrayList<String> al=new ArrayList<String>();
	Context context;

	public MyAdapter(Context context, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.al=al;
		this.context=context;
	}
  //得到要显示数据的条数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.al.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    //决定每条数据的样式,它会被调用若干次
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//加载table.xml格式化每条数据
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.table, null);
		
		TextView tvUserName=(TextView)ll.findViewById(R.id.tv_userName);
		tvUserName.setText(al.get(position));
		
		TextView tvTel=(TextView)ll.findViewById(R.id.tv_tel);
		tvTel.setText("1390000000"+position);
		
		return ll;
	}

}
