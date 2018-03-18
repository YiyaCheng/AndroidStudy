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
		//����ContentResolver�������ContentProvder�ṩ������
		cr=getContentResolver();
		//����Uri���������ContentProvder�����Ľӿ�
		//inbox �ռ��� outbox ������ draft �ݸ���
		String strUri="content://sms/inbox";
		Uri uri=Uri.parse(strUri);
		Cursor c=cr.query(uri, new String[]{"address","body"}
		,null , null, null);
		String str="";
		while(c.moveToNext()){
			for(int i=0;i<c.getColumnCount();i++){
				String colName=c.getColumnName(i);
				str=str+"�ֶ����ƣ�"+colName+"="+c.getString(i);
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
