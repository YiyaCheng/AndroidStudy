package jxnu.edu.cn.x3321;

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
		//inbox 收件箱 outbox 发件箱 draft 草稿箱
		String strUri="content://sms/inbox";
		Uri uri=Uri.parse(strUri);
		Cursor c=cr.query(uri, new String[]{"address","body"}
		,null , null, null);
		String str="";
		while(c.moveToNext()){
			for(int i=0;i<c.getColumnCount();i++){
				String colName=c.getColumnName(i);
				str=str+"字段名称："+colName+"="+c.getString(i);
			}
			str=str+"\n";			
		}
		c.close();
		tv.setText(str);
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
