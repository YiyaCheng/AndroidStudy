package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import jxnu.edu.cn.x3321.adapter.GraidAdapter;

public class MainActivity extends Activity {

	GridView gridview;
	String []button={"Button","Button","Button","Button","Button","Button",
			"Button","Button","Button","Button","Button","Button","Button",
			"Button","Button","Button","Button","Button"};
	String []text={"1","2","3","4","5","6","7","8","9",
			"10","11","12","13","14","15","16","17","18"};
	Button []bt={};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		final GraidAdapter gridAdapter =new GraidAdapter(getApplication(),button,text);
		gridview.setAdapter(gridAdapter);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		gridview=(GridView)findViewById(R.id.grid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
