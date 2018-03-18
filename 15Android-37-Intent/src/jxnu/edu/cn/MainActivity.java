package jxnu.edu.cn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button bt1,bt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inint();
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//第一种启动组件方式
				Intent intent=new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				
				//第二种启动组件方式
				/*
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				*/
				
				//第三种启动组件方式
				/*
				Intent intent=new Intent();
				intent.setClassName("jxnu.edu.cn", "jxnu.edu.cn.SecondActivity");
				startActivity(intent);
				*/
								
				finish();
			}
		});
		
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClassName("com.frank.ec2012", 
						"com.frank.ec2012.EuropeCup2012Activity");
				startActivity(intent);
			}
		});
	}

	private void inint() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
		bt2=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
