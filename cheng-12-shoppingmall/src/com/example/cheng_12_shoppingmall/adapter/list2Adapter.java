package com.example.cheng_12_shoppingmall.adapter;

import java.util.ArrayList;

import com.example.cheng_12_shoppingmall.Goods;
import com.example.cheng_12_shoppingmall.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class list2Adapter extends BaseAdapter {

	Context context;
	LinearLayout ll;
	int i;
	//int[]images;
	//String[]textTitle,prices,textContent;
	//public list2Adapter(Context context, int i,LinearLayout ll,int []images,String[]textTitle,String[]prices,String[]textContent)
    ArrayList<Goods> Goods;
	
	public list2Adapter(Context context,int i,LinearLayout ll,ArrayList<Goods> goods)
	{
		// TODO Auto-generated constructor stub
		this.context=context;
		this.i=i;
		/*this.images=images;
		this.prices=prices;
		this.textContent=textContent;
		this.textTitle=textTitle;*/
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
		
		/*ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
		image.setImageResource(images[i]);
		
		TextView headLine=(TextView)ll.findViewById(R.id.headline);
		headLine.setText(textTitle[i]);
		
		TextView price=(TextView)ll.findViewById(R.id.prices);
		price.setText(prices[i]);
		
		TextView depict=(TextView)ll.findViewById(R.id.depict);
		depict.setText(textContent[i]);*/
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
