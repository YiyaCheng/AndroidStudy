package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactActivity extends Activity {
	//�������
	EditText etUpdateName,etUpdatePhone;
	Button btUpdateUser,btUpdateCancel;
	UserInterface ui; 
	int userid;
	User user=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_contact);
		//2.��ʼ��
		init();
		//3.��intent�����л��Ҫ�޸���ϵ�˵Ĺؼ���userid
		userid=this.getIntent().getIntExtra("userid", -10000);
		//4.���ݹؼ���userid�����ݿ��user������ȡ��Ҫ�޸ĵ���ϵ�ˣ����ڽ�������ʾ
		ui=new UserInterfaceImp(getApplicationContext());
		user=ui.getUserById(userid);
		if(user!=null){
			etUpdateName.setText(user.getUserName());
			etUpdatePhone.setText(user.getPhone());
		}else{
			Toast.makeText(getApplicationContext(),
					"��ϵ�˲�����!", Toast.LENGTH_SHORT)
					.show();
		}
		//5.����ע���¼�������
		btUpdateUser.setOnClickListener(new UpdateUser());
	
	}
	
	public class UpdateUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//6.�����ϵ����Ϣ
			String name=etUpdateName.getText().toString().trim();
			String phone=etUpdatePhone.getText().toString().trim();
			if(name==null||name.equals("")||phone==null||phone.equals("")){
				Toast.makeText(getApplicationContext(), 
						"��������ȷ���û��������룡", Toast.LENGTH_SHORT)
						.show();
			}else{
				//7.�����ݷ�װ��User������
				User user=new User();
				user.setUserid(userid);
				user.setUserName(name);
				user.setPhone(phone);
				//8.��user����д�������棩���ݿ�contact��user����
				ui=new UserInterfaceImp(getApplicationContext());
				if(ui.update(user)!=0){
					Toast.makeText(getApplicationContext(), 
							"��ϵ���޸ĳɹ���", Toast.LENGTH_SHORT)
							.show();
					Intent intent=new Intent(UpdateContactActivity.this,
							MyContactActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), 
							"��ϵ���޸Ĳ��ɹ���", Toast.LENGTH_SHORT)
							.show();
					etUpdateName.requestFocus();
				}
			}
		}
		
	}
	private void init() {
		// TODO Auto-generated method stub
		etUpdateName=(EditText)this.findViewById(R.id.etUpdateName);
		etUpdatePhone=(EditText)this.findViewById(R.id.etUpdatePhone);
		btUpdateUser=(Button)this.findViewById(R.id.btUpdateUser);
		btUpdateCancel=(Button)this.findViewById(R.id.btUpdateCancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_contact, menu);
		return true;
	}

}
