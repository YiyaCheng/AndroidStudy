package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义成员变量
	EditText etUserName,etpassWord,etAge;
	RadioGroup rg;
	RadioButton rbBoy,rbGirl;
	CheckBox cbRead,cbFootBall,cbBasketBall;
	Button btRegedit,btCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.定义、注册事件监听器
		btRegedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName,passWord,strAge;
				int age=0;
				String sex="男",hobbys="";
				userName=etUserName.getText().toString().trim();
				passWord=etpassWord.getText().toString().trim();
				strAge=etAge.getText().toString().trim();
				if(strAge.equals("")||strAge==null){
					Toast.makeText(getApplicationContext(), "年龄不能为空！",
							Toast.LENGTH_SHORT).show();
				}else{
					age=Integer.parseInt(strAge);
				}
				if(rbGirl.isChecked()){
					sex="女";
				}
				if(cbRead.isChecked()){
					hobbys=hobbys+cbRead.getText().toString()+"  ";
				}
				if(cbFootBall.isChecked()){
					hobbys=hobbys+cbFootBall.getText().toString()+"  ";
				}
				if(cbBasketBall.isChecked()){
					hobbys=hobbys+cbBasketBall.getText().toString()+"  ";
				}
				//定义对话框
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("你的注册信息");
				builder.setMessage(userName+"\n"
				                   +passWord+"\n"
				                   +age+"\n"
				                   +sex+"\n"
				                   +hobbys);
				builder.setPositiveButton("确定", null);
				builder.setNegativeButton("取消",null);
				AlertDialog ad=builder.create();
				ad.show();
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
	    etpassWord=(EditText)this.findViewById(R.id.et_passWord);
	    etAge=(EditText)this.findViewById(R.id.et_age);
	    rg=(RadioGroup)this.findViewById(R.id.rg_sex);
	    rbBoy=(RadioButton)this.findViewById(R.id.rb_boy);
	    rbGirl=(RadioButton)this.findViewById(R.id.rb_girl);
	    cbRead=(CheckBox)this.findViewById(R.id.cb_read);
	    cbFootBall=(CheckBox)this.findViewById(R.id.cb_football);
	    cbBasketBall=(CheckBox)this.findViewById(R.id.cb_basketball);
	    btRegedit=(Button)this.findViewById(R.id.bt_regedit);
	    btCancel=(Button)this.findViewById(R.id.bt_cancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
