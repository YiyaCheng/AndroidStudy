package jnxu.edu.cn.x3321;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;
	ContentResolver cr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		//定义ContentResolver对象接收ContentProvder提供的内容
		cr=getContentResolver();
		//定义Uri对象来获得ContentProvder公布的接口
	//	String strUri="content://jnxu.edu.cn.x3321/all";
		String strUri="content://jnxu.edu.cn.x3321/2";
		queryAll(strUri);
	}

	private void queryAll(String strUri) {
		// TODO Auto-generated method stub
		Uri uri=Uri.parse(strUri);
		Cursor cursor=cr.query(uri, null, null, null, null);
		while(cursor.moveToNext()){
			int userId=cursor.getInt(cursor.getColumnIndex("userid"));
			String name=cursor.getString(cursor.getColumnIndex("userName"));
			tv.setText(name);
		}
		cursor.close();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
