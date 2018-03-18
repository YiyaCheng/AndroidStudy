package com.example.cheng_13_fragmentshoppingmall_01.adapter;


import java.util.ArrayList;

import com.example.cheng_13_fragmentshoppingmall_01.R;
import com.example.cheng_13_fragmentshoppingmall_01.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cheng_13_fragmentshoppingmall_01.Data;
public class detail1Adapter extends BaseAdapter {

	Context context;
	Data data=new Data();
	ArrayList<Goods> Goods;
	
	public detail1Adapter(Context context,ArrayList<Goods> goods){
		// TODO Auto-generated constructor stub
		this.context=context;
		this.Goods=goods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return data.images.length;
		return Goods.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		//return data.images[arg0];
		return Goods.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		/*LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listlayout,null);
		
		ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(data.images[arg0]);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(data.textTitle[arg0]);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(data.prices[arg0]);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(data.textContent[arg0]);*/
        LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listlayout,null);
		
		ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(Goods.get(arg0).images);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(Goods.get(arg0).textTitle);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(Goods.get(arg0).prices);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(Goods.get(arg0).textContent);
		return ll;
	}


}
