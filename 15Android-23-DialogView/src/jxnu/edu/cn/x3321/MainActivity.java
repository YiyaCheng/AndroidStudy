package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义成员变量
	Button bt;
	EditText etUserName,etPassWord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3定义注册事件监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=creteDialog();
				ad.show();
			}
		});
	}

	protected AlertDialog creteDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		ad.setTitle("自定义View对话框");
		ad.setIcon(R.drawable.ic_launcher);
		
		//加载自定义的login.xml到程序中
		final LinearLayout ll=(LinearLayout)getLayoutInflater()
				.inflate(R.layout.login, null);
		
		//把login.xml加载到Dialog中
		ad.setView(ll);
		
		ad.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etUserName=(EditText)ll.findViewById(R.id.et_userName);
				etPassWord=(EditText)ll.findViewById(R.id.et_passWord);
				Toast.makeText(getApplicationContext(), "你登录的信息就："+"\n"
				+etUserName.getText().toString()+"\n"
				+etPassWord.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		ad.setNegativeButton("取消",null);
		
		alertDialog=ad.create();
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassWord=(EditText)this.findViewById(R.id.et_passWord);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
