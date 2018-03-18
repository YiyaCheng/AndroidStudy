package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.ExpandableListView.OnChildClickListener;
import jxnu.edu.cn.x3321.adapter.ExpandAdapter;

public class MainActivity extends Activity {
   
	Button bt;
	ExpandableListView elv;
	String[] colors=new String[]{"blue","green","yellow","red","black"};
	int [][] fillColor=new int[][]{{Color.BLUE},{Color.GREEN},{Color.YELLOW},{Color.RED},{Color.BLACK}};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog dialog=createDialog();
				dialog.show();
			}
		});
	}

protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
	AlertDialog alertDialog;
	AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
	dialog.setTitle("choose your BackGroundColor");
	
	LinearLayout ll=(LinearLayout)getLayoutInflater().inflate(R.layout.dialog_expandlistview,null);
	dialog.setView(ll);
	
	
	ExpandAdapter my=new ExpandAdapter(getApplicationContext(),colors,fillColor);
	elv=(ExpandableListView)ll.findViewById(R.id.expand);
	elv.setAdapter(my);
	elv.setOnChildClickListener(new OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
			LinearLayout main=(LinearLayout)MainActivity.this.findViewById(R.id.mainll);
			main.setBackgroundColor(fillColor[groupPosition][childPosition]);
			//Toast.makeText(MainActivity.this, "byebyeworld", 1).show();  
			return true;
		}
	});
	
	
	alertDialog=dialog.create();
	return alertDialog;
	}

private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		elv=(ExpandableListView)this.findViewById(R.id.expand);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
