package com.example.cheng_15_navigation_tabhost.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.*;
import com.example.cheng_15_navigation_tabhost.*;
public class list2Adapter extends BaseAdapter {

	Context context;
	LinearLayout ll;
	int i;
    ArrayList<Goods> Goods;
	
	public list2Adapter(Context context,int i,LinearLayout ll,ArrayList<Goods> goods)
	{
		// TODO Auto-generated constructor stub
		this.context=context;
		this.i=i;
		this.ll=ll;
		this.Goods=goods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ll;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listlayout,null);
        
		ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(Goods.get(i).images);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(Goods.get(i).textTitle);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(Goods.get(i).prices);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(Goods.get(i).textContent);
		return ll;
	}
}
