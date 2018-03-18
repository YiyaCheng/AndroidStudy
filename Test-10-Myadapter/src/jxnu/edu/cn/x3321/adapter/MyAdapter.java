package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.Student;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MyAdapter extends BaseAdapter {
	ArrayList<Student> al=new ArrayList<Student>();
	Context context;
	int []images;
	String []names;
	String []ids;
    public  MyAdapter(Context context,ArrayList<Student> al,int images[],String names[],String ids[])
    {
    	this.context=context;
    	this.al=al;
    	this.images=images;
    	this.names=names;
    	this.ids=ids;
    	
    }
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.table, null);
		
		ImageView image=(ImageView)ll.findViewById(R.id.image);
		image.setImageResource(images[position]);
		
		TextView tvUserName=(TextView)ll.findViewById(R.id.tv_userName);
		tvUserName.setText(names[position]);
		
		TextView tvTel=(TextView)ll.findViewById(R.id.tv_id);
		tvTel.setText(ids[position]);
		
		return ll;
	}

}
