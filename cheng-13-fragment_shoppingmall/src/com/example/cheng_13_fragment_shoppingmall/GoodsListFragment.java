package com.example.cheng_13_fragment_shoppingmall;


import com.example.cheng_13_fragment_shoppingmall.adapter.list1Adapter;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

//public class GoodsListFragment extends ListFragment {
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
	list1Adapter la;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.goodslistfragment, container,false);
		list1Adapter la=new list1Adapter(getActivity(),images,textTitle,prices,textContent);
		ListView lv1=(ListView)view.findViewById(R.id.listview1);
		lv1.setAdapter(la);
		return view;
	}
}
