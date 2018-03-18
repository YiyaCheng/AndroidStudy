package com.example.cheng_13_fragmentshoppingmall_01.adapter;


import com.example.cheng_13_fragmentshoppingmall_01.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.cheng_13_fragmentshoppingmall_01.Data;
public class detail2Adapter extends BaseAdapter {

	Context context;
	Data data=new Data();
	int i;
	View view;
	public detail2Adapter(Context context,int i,View view){
		// TODO Auto-generated constructor stub
		this.context=context;
		this.i=i;
		this.view=view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.listlayout,null);
			
			ImageView image=(ImageView)ll.findViewById(R.id.goodsImage);
			image.setImageResource(data.images[i]);
			
			TextView headLine=(TextView)ll.findViewById(R.id.headline);
			headLine.setText(data.textTitle[i]);
			
			TextView price=(TextView)ll.findViewById(R.id.prices);
			price.setText(data.prices[i]);
			
			TextView depict=(TextView)ll.findViewById(R.id.depict);
			depict.setText(data.textContent[i]);
			return ll;
	}


}
