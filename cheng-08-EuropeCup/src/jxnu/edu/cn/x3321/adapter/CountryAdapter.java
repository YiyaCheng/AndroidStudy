package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.Country;
import jxnu.edu.cn.x3321.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class CountryAdapter extends BaseAdapter {
	Context context;
	//ArrayList<Country> ct=new ArrayList<Country>();
	//int []images;
	//String[]names;
	int [][]images;
	String[][]names;
	//public CountryAdapter(Context context,ArrayList<Country> ct,int images[],String names[])
	public CountryAdapter(Context context,int images[][],String names[][])
	{
		this.context=context;
		//this.ct=ct;
		this.images=images;
		this.names=names;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return this.ct.size();
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return this.ct.get(position);
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout linear=(LinearLayout)View.inflate(context, R.layout.country,null);
		
		ImageView image1=(ImageView)linear.findViewById(R.id.image1);
		//image1.setImageResource(ct.get(position).getImage());
		image1.setImageResource(images[position][0]);
		TextView tv1=(TextView)linear.findViewById(R.id.tv1);
		//tv1.setText(ct.get(position).getName());
		tv1.setText(names[position][0]);
		
		ImageView image2=(ImageView)linear.findViewById(R.id.image2);
		//image2.setImageResource(ct.get(position).getImage());
		image2.setImageResource(images[position][1]);
		TextView tv2=(TextView)linear.findViewById(R.id.tv2);
		//tv2.setText(ct.get(position).getName());
		tv2.setText(names[position][1]);
		
		ImageView image3=(ImageView)linear.findViewById(R.id.image3);
		//image3.setImageResource(ct.get(position).getImage());
		image3.setImageResource(images[position][2]);
		TextView tv3=(TextView)linear.findViewById(R.id.tv3);
		//tv3.setText(ct.get(position).getName());
		tv3.setText(names[position][2]);
		
		ImageView image4=(ImageView)linear.findViewById(R.id.image4);
		//image4.setImageResource(ct.get(position).getImage());
		image4.setImageResource(images[position][3]);
		TextView tv4=(TextView)linear.findViewById(R.id.tv4);
		//tv4.setText(ct.get(position).getName());
		tv4.setText(names[position][3]);
		
		return linear;
	}

}
