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
			"�ʼǱ�˫���","�����������FSW52R","������D-9"};
	String[] prices={"��3499","��4299","��149","��199","��398"};
	String[] textContent={
			"���������ʹ��A5X˫�˴��������ĺ�GPU",
			"ThinkPad X230i(2306-6FC) 12.5Ӣ��ʼǱ�",
			"ThinkPadԭװRadiant�����ͱʼǱ�˫�米��78Y23790",
			"���ܾ����������ۣ����˼�֤Ʒ�ʱ��ϣ�������ʱ ȫ��ң�� �����Լ۱ȣ�",
			"ȫ�������ھ�����������ĿΨһ��25000��������1800Wǿ�����ʣ��Լ۱�֮�������ж���Ż���װ��"};
	
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
				Toast.makeText(getActivity(), "����Ʒ����ӵ����ﳵ", Toast.LENGTH_LONG).show();
				return false;
			}
		});
		return view;
	}
	
}
