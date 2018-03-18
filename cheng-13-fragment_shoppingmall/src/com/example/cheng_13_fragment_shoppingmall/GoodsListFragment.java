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
			"�ʼǱ�˫���","�����������FSW52R","������D-9"};
	String[] prices={"��3499","��4299","��149","��199","��398"};
	String[] textContent={
			"���������ʹ��A5X˫�˴��������ĺ�GPU",
			"ThinkPad X230i(2306-6FC) 12.5Ӣ��ʼǱ�",
			"ThinkPadԭװRadiant�����ͱʼǱ�˫�米��78Y23790",
			"���ܾ����������ۣ����˼�֤Ʒ�ʱ��ϣ�������ʱ ȫ��ң�� �����Լ۱ȣ�",
			"ȫ�������ھ�����������ĿΨһ��25000��������1800Wǿ�����ʣ��Լ۱�֮�������ж���Ż���װ��"};
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
