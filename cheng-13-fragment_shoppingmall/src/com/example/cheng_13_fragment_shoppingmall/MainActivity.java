package com.example.cheng_13_fragment_shoppingmall;

import android.os.Bundle;
import android.app.*;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener{

	FragmentManager manager;
	FragmentTransaction transaction;
	RadioButton rbGoods,rbSearch,rbCar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		manager=getFragmentManager();
		transaction=manager.beginTransaction();
		transaction.add(R.id.content,new GoodsListFragment());
		transaction.commit();
		
		init();
		rbGoods.setOnClickListener(this);
		rbSearch.setOnClickListener(this);
		rbCar.setOnClickListener(this);
	}

	public void onClick(View view)
	{
		transaction=manager.beginTransaction();
		switch(view.getId()){
		case R.id.rb_goods:
			transaction.replace(R.id.content,new GoodsListFragment());
			break;
		case R.id.rb_search:
			transaction.replace(R.id.content,new SearchFragment());
			break;
		case R.id.rb_car:
			transaction.replace(R.id.content,new ShoppingCarFragment());
			break;
		default:
			break;
		}
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

}
