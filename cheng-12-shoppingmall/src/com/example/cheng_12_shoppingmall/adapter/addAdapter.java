package com.example.cheng_12_shoppingmall.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.cheng_12_shoppingmall.Goods;
import com.example.cheng_12_shoppingmall.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class addAdapter extends BaseAdapter {

	Context context;
	ArrayList<Goods> shoppinggoods;
	
	public addAdapter(Context context,ArrayList<Goods> shoppinggoods){
		this.context=context;
		this.shoppinggoods=shoppinggoods;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return shoppinggoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return shoppinggoods.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listlayout,null);
		ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(shoppinggoods.get(position).images);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(shoppinggoods.get(position).textTitle);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(shoppinggoods.get(position).prices);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(shoppinggoods.get(position).textContent);
		return ll;
	}

}
