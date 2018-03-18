package com.example.cheng_13_fragmentshoppingmall_01;


import java.util.ArrayList;


import com.example.cheng_13_fragmentshoppingmall_01.adapter.detail1Adapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsListFragment extends Fragment {

	int[]images={R.drawable.a,R.drawable.b,R.drawable.c,
			R.drawable.d,R.drawable.e};
	String[] textTitle={"Ipad","ThinkPad X230i",
			"笔记本双肩包","艾美特落地扇FSW52R","吸尘器D-9"};
	String[] prices={"￥3499","￥4299","￥149","￥199","￥398"};
	String[] textContent={
			"电池容量大，使用A5X双核处理器，四核GPU",
			"ThinkPad X230i(2306-6FC) 12.5英寸笔记本",
			"ThinkPad原装Radiant典雅型笔记本双肩背包78Y23790",
			"领跑京东风扇销售！万人见证品质保障！超长定时 全能遥控 超高性价比！",
			"全网销量冠军，吸尘器类目唯一近25000条好评！1800W强劲功率，性价比之王！更有多款优惠套装！"};
	
	ListView list1;
    ArrayList<Goods> goods=new ArrayList<Goods>();
    ArrayList<Goods> shoppinggoods=new ArrayList<Goods>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_goodslist,container,false);
		goods.clear();
		for(int i=0;i<images.length;i++)
		{
			goods.add(new Goods(images[i],textTitle[i],textContent[i],prices[i]));
			shoppinggoods.add(new Goods(images[i],textTitle[i],textContent[i],prices[i]));
		}
		detail1Adapter adapter = new detail1Adapter (getActivity(),goods);  
		list1=(ListView)view.findViewById(R.id.goodslist);
		
        list1.setAdapter(adapter);
       
        list1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				((MainActivity)getActivity()).setGoods(shoppinggoods.get(arg2));
				Toast.makeText(getActivity(), "该商品已添加到购物车", Toast.LENGTH_LONG).show();
				return false;
			}
		});
		return view;
	}
	
}
