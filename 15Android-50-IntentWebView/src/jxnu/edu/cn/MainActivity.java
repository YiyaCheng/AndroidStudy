package jxnu.edu.cn;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�������
	EditText etUrl;
	Button btGo;
	WebView wv;
	String urlStr="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.����ע���¼�������
		btGo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				urlStr="http://";
				String url=etUrl.getText().toString().trim();
				if(url==null||url.equals("")){
					Toast.makeText(getApplicationContext(), "��������ַ!",
							Toast.LENGTH_SHORT).show();
				}else{
					urlStr=urlStr+url;
					//��androidManfiest.xml�����ӷ�������Ȩ��
					//��һ�ּ�����ҳ��ʽ
					//wv.loadUrl(urlStr);
					//�ڶ��ּ�����ҳ��ʽ
					Uri uri=Uri.parse(urlStr);
					Intent intent=new Intent(Intent.ACTION_VIEW,uri);
					startActivity(intent);
				}
			}
		});
		
		//�����ּ�����ҳ��ʽ
		wv.loadData("<html>"+
		               "<body>"
		               + "<a href=\"http://www.sohu.com\">www.sohu.com</a>"
		               +"</body>"
		               +"</html>", "<text/html>","utf-8");
	}

	private void init() {
		// TODO Auto-generated method stub
		etUrl=(EditText)this.findViewById(R.id.et_url);
		btGo=(Button)this.findViewById(R.id.bt_go);
		wv=(WebView)this.findViewById(R.id.wv);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
