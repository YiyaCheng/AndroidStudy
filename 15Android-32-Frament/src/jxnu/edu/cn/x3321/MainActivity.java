package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	//1.�������
	Button btQq,btWeixin;
	QqFragment qqFragment;
	WeixinFragment weixinFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		inint();
	    //3.����ȱʡ��fragment
		setDefaultFragment();
		//4.���塢ע���¼�������
		btQq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qqFragment=new QqFragment();
				FragmentManager manager=getFragmentManager();
				FragmentTransaction transcation=manager.beginTransaction();
				transcation.replace(R.id.fragment_content, qqFragment);
				transcation.commit();
			}
		});
		
		btWeixin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				weixinFragment=new WeixinFragment();
				FragmentManager manager=getFragmentManager();
				FragmentTransaction transcation=manager.beginTransaction();
				transcation.replace(R.id.fragment_content, weixinFragment);
				transcation.commit();
			}
		});
		
	}

	private void setDefaultFragment() {
		// TODO Auto-generated method stub
		weixinFragment=new WeixinFragment();
		FragmentManager manager=getFragmentManager();
		FragmentTransaction transcation=manager.beginTransaction();
		transcation.replace(R.id.fragment_content, weixinFragment);
		transcation.commit();
	}

	private void inint() {
		// TODO Auto-generated method stub
		btQq=(Button)this.findViewById(R.id.bt_qq);
		btWeixin=(Button)this.findViewById(R.id.bt_weixin);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
