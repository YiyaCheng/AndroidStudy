package com.example.cheng_13_fragmentshoppingmall_01;



import java.util.ArrayList;

import com.example.cheng_13_fragmentshoppingmall_01.adapter.detail1Adapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ShoppingCarFragment extends Fragment {

	ArrayList<Goods> shoppinggoods=new ArrayList<Goods>();
	detail1Adapter la3;
	ListView list3;
	
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_shoppingcar,container,false);
		list3=(ListView)view.findViewById(R.id.shoppingcarlist);
		Goods goods=((MainActivity)getActivity()).getGoods();
		if(goods.images==0){
			la3=new detail1Adapter(getActivity(),shoppinggoods);
			list3.setAdapter(la3);
        }
		else
		{
			shoppinggoods.add(goods);
			la3=new detail1Adapter(getActivity(),shoppinggoods);
			list3.setAdapter(la3);
		}
		list3.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				shoppinggoods.remove(arg2);
				la3.notifyDataSetChanged();
				Toast.makeText(getActivity(), "该商品已从购物车移除", Toast.LENGTH_LONG).show();
				return true;
			}
		});

		
		return view;
	}
}
