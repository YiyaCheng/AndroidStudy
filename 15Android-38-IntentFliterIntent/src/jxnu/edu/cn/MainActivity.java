package jxnu.edu.cn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button bt1,bt2;
	static String SENCODACTIVITY="jxnu.edu.cn.SA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inint();
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction(SENCODACTIVITY);
			//	intent.setAction("jxnu.edu.cn.SA");
				startActivity(intent);
				
				finish();
			}
		});
	}

	private void inint() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
