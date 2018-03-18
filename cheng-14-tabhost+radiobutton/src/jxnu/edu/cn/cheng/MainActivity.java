package jxnu.edu.cn.cheng;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;


public class MainActivity extends TabActivity implements OnCheckedChangeListener {

	TabHost tabhost;
	RadioGroup rg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		tabhost=getTabHost();
		TabSpec tab1=tabhost.newTabSpec("tab1");
		tab1.setContent(R.id.ltab1);
		tab1.setIndicator("新闻");
		
		TabSpec tab2=tabhost.newTabSpec("tab2");
		tab2.setContent(R.id.ltab2);
		tab2.setIndicator("话题");
		
		TabSpec tab3=tabhost.newTabSpec("tab3");
		tab3.setContent(R.id.ltab3);
		tab3.setIndicator("图片");
		
		TabSpec tab4=tabhost.newTabSpec("tab4");
		tab4.setContent(R.id.ltab4);
		tab4.setIndicator("投票");
		
		TabSpec tab5=tabhost.newTabSpec("tab5");
		tab5.setContent(R.id.ltab5);
		tab5.setIndicator("跟贴");
		
		tabhost.addTab(tab1);
		tabhost.addTab(tab2);
		tabhost.addTab(tab3);
		tabhost.addTab(tab4);
		tabhost.addTab(tab5);
		
		rg.setOnCheckedChangeListener(this);
		
	}
    
	private void init() {
		// TODO Auto-generated method stub
		rg=(RadioGroup)this.findViewById(R.id.rg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
	        switch (checkedId) {
	        case R.id.rg_news:
	            tabhost.setCurrentTabByTag("tab1");
	            break;
	        case R.id.rg_topic:
	            tabhost.setCurrentTabByTag("tab2");
	            break;
	        case R.id.rg_picture:
	            tabhost.setCurrentTabByTag("tab3");
	            break;
	        case R.id.rg_vote:
	            tabhost.setCurrentTabByTag("tab4");
	            break;    
	        case R.id.rg_comment:
	            tabhost.setCurrentTabByTag("tab5");
	            break;    
	        default:
	            break;
	        }

	    }
	}

