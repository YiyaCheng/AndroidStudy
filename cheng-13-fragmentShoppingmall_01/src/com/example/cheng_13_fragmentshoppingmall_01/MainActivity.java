package com.example.cheng_13_fragmentshoppingmall_01;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.app.*;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class MainActivity extends Activity implements OnClickListener {

	RadioButton rbGoods,rbSearch,rbCar;
	GoodsListFragment goodsListFragment=new GoodsListFragment();
	NavigateFragment navigateFragment=new NavigateFragment();
	SearchFragment searchFragment=new SearchFragment();
	ShoppingCarFragment shoppingCarFragment=new ShoppingCarFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		
		setFirstFragment();
		
		
		rbGoods.setOnClickListener(this);
		rbSearch.setOnClickListener(this);
		rbCar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.rb_goods:
			replaceFragment(goodsListFragment);
			rbGoods.setBackgroundColor(Color.parseColor("#EE7942"));
			rbSearch.setBackgroundColor(Color.parseColor("#242424"));
			rbCar.setBackgroundColor(Color.parseColor("#242424"));
			break;
		case R.id.rb_search:
			replaceFragment(searchFragment);
			rbSearch.setBackgroundColor(Color.parseColor("#EE7942"));
			rbGoods.setBackgroundColor(Color.parseColor("#242424"));
			rbCar.setBackgroundColor(Color.parseColor("#242424"));
			break;
		case R.id.rb_car:
			replaceFragment(shoppingCarFragment);
			rbCar.setBackgroundColor(Color.parseColor("#EE7942"));
			rbSearch.setBackgroundColor(Color.parseColor("#242424"));
			rbGoods.setBackgroundColor(Color.parseColor("#242424"));
			break;
		default:
			break;
		}
	}
	
	
	private void setFirstFragment() {
		// TODO Auto-generated method stub
		replaceFragment(goodsListFragment);
	}

	private void replaceFragment(Fragment fragment)
	{
		FragmentManager manager=getFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.content,fragment);
		transaction.commit();
	}
	private void init() {
		// TODO Auto-generated method stub
		rbGoods=(RadioButton)findViewById(R.id.rb_goods);
		rbSearch=(RadioButton)findViewById(R.id.rb_search);
		rbCar=(RadioButton)findViewById(R.id.rb_car);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//ArrayList<Goods> goods=new ArrayList<Goods>();
	Goods goods=new Goods(0,"","","");
	public Goods getGoods(){
        return goods;
    }
    public void setGoods(Goods goods){
        this.goods=goods;
    }	
}
	


