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
	//1.�������
	EditText etAddName,etAddPhone;
	Button btAddUser,btAddCancel;
	UserInterface ui;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		//2.��ʼ��
		init();
		//3.����ע���¼�������
		btAddUser.setOnClickListener(new AddUser());
	}

	public class AddUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//4.�����ϵ����Ϣ
			String name=etAddName.getText().toString().trim();
			String phone=etAddPhone.getText().toString().trim();
			if(name==null||name.equals("")||phone==null||phone.equals("")){
				Toast.makeText(getApplicationContext(), 
						"��������ȷ���û��������룡", Toast.LENGTH_SHORT)
						.show();
			}else{
				//5.�����ݷ�װ��User������
				User user=new User();
				user.setUserName(name);
				user.setPhone(phone);
				//6.��user����д�������棩���ݿ�contact��user����
				ui=new UserInterfaceImp(getApplicationContext());
				if(ui.insert(user)==1)
				{
					Toast.makeText(getApplicationContext(), 
							"��ϵ�˱���ɹ���", Toast.LENGTH_SHORT)
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
