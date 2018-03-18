package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//�������
	EditText etUserName,etPassWord;
	CheckBox cbRember,cbShow;
	Button btLogin,btCancel;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2��ʼ��
		init();
		//3.ͨ��sp���󴴽����/data/data/SharedPreferences/user.xml
		sp=this.getSharedPreferences("user", Context.MODE_PRIVATE);
		//4.һ���������Ҫ��user.xml�ж�ȡ��ʼ��������Ϣ
		boolean isRember=sp.getBoolean("isRember", false);
		if(isRember){
			etUserName.setText(sp.getString("name",""));
			etPassWord.setText(sp.getString("passWord",""));
			cbShow.setChecked(sp.getBoolean("isShow", false));
			cbRember.setChecked(sp.getBoolean("isRember", false));
			if(sp.getBoolean("isShow", false)){
				etPassWord.setTransformationMethod(
						HideReturnsTransformationMethod.getInstance());
			}
		}
		
		
		//5.����ע���¼�������
		cbRember.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ͨ��Editor ���������д��user.xml��
				Editor edit=sp.edit();
				//����û����������ֵ
				String userName=etUserName.getText().toString().trim();
				String passWord=etPassWord.getText().toString().trim();
				if(cbRember.isChecked()){
					edit.putString("name", userName);
					edit.putString("passWord", passWord);
					edit.putBoolean("isRember", true);
					edit.putBoolean("isShow", cbShow.isChecked());
					edit.commit();
				}else{
					edit.putString("name", "");
					edit.putString("passWord", "");
					edit.putBoolean("isRember", false);
					edit.putBoolean("isShow", cbShow.isChecked());
					edit.commit();
				}
			}
		});
		cbShow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ͨ��Editor ���������д��user.xml��
				Editor edit=sp.edit();
				if(cbShow.isChecked()){
					edit.putBoolean("isShow", true);
					edit.commit();
					etPassWord.setTransformationMethod(
							HideReturnsTransformationMethod.getInstance());
				}else{
					edit.putBoolean("isShow", false);
					edit.commit();
					etPassWord.setTransformationMethod(
							PasswordTransformationMethod.getInstance());
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassWord=(EditText)this.findViewById(R.id.et_passWord);
		cbRember=(CheckBox)this.findViewById(R.id.cb_rember);
		cbShow=(CheckBox)this.findViewById(R.id.cb_show);
		btLogin=(Button)this.findViewById(R.id.bt_login);
		btCancel=(Button)this.findViewById(R.id.bt_cancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
