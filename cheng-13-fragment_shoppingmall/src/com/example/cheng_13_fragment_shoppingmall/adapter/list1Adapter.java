package com.example.cheng_13_fragment_shoppingmall.adapter;

import com.example.cheng_13_fragment_shoppingmall.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class list1Adapter extends BaseAdapter {

	Context context;
	int []images;
	String[] textTitle;
	String[] textContent;
	String []prices;
	public list1Adapter(Context context, int []images, String[] textTitle,String[] prices,  String[] textContent) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.images=images;
		this.textTitle=textTitle;
		this.prices=prices;
		this.textContent=textContent;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return images[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listview,null);
		
		ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(images[arg0]);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(textTitle[arg0]);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(prices[arg0]);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(textContent[arg0]);
		return ll;
	}

}
