package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends Activity {
	//1.定义变量
	EditText etAddName,etAddPhone;
	Button btAddUser,btAddCancel;
	UserInterface ui;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		//2.初始化
		init();
		//3.定义注册事件监听器
		btAddUser.setOnClickListener(new AddUser());
	}

	public class AddUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//4.获得联系人信息
			String name=etAddName.getText().toString().trim();
			String phone=etAddPhone.getText().toString().trim();
			if(name==null||name.equals("")||phone==null||phone.equals("")){
				Toast.makeText(getApplicationContext(), 
						"请输入正确的用户名和密码！", Toast.LENGTH_SHORT)
						.show();
			}else{
				//5.把数据封装到User对象中
				User user=new User();
				user.setUserName(name);
				user.setPhone(phone);
				//6.把user对象写到（保存）数据库contact的user表中
				ui=new UserInterfaceImp(getApplicationContext());
				if(ui.insert(user)==1)
				{
					Toast.makeText(getApplicationContext(), 
							"联系人保存成功！", Toast.LENGTH_SHORT)
							.show();
					Intent intent=new Intent(AddContactActivity.this,
							MyContactActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		etAddName=(EditText)this.findViewById(R.id.etAddName);
		etAddPhone=(EditText)this.findViewById(R.id.etAddPhone);
		btAddUser=(Button)this.findViewById(R.id.btAddUser);
		btAddCancel=(Button)this.findViewById(R.id.btAddCancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

}
