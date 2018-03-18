package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	//1.�������
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		//2��ʼ��
		tabHost=getTabHost();
		//3.������һ��tabҳ
		TabSpec tab1=tabHost.newTabSpec("tab1");
		tab1.setContent(R.id.ltab1);
		tab1.setIndicator("��ϵ��");
		//3.�����ڶ���tabҳ
		TabSpec tab2=tabHost.newTabSpec("tab2");
		tab2.setContent(R.id.ltab2);
		tab2.setIndicator("��    Ϣ");
		//3.����������tabҳ
		TabSpec tab3=tabHost.newTabSpec("tab3");
		tab3.setContent(R.id.ltab3);
		tab3.setIndicator("��     ��");
		//3.�������ĸ�tabҳ
		TabSpec tab4=tabHost.newTabSpec("tab4");
		tab4.setContent(R.id.ltab4);
		tab4.setIndicator("���ͨ��");
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
