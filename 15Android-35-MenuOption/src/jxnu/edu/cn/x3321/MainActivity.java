package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	//1.�������
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ�� 
		inint();
	}

	private void inint() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("�˵���1");
		menu.add("�˵���2");
		menu.add("�˵���3");
		menu.add(0,1,0,"�˵���4");
		menu.add(0,2,0,"�˵���5");
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String result="��ѡ����ǣ�";
		if(item.getTitle().equals("�˵���1")){
			result=result+item.getTitle();
		}else if(item.getTitle().equals("�˵���2")){
			result=result+item.getTitle();
		}else if(item.getTitle().equals("�˵���3")){
			result=result+item.getTitle();
		}
		
		tv.setText(result);
		return super.onOptionsItemSelected(item);
	}

}
