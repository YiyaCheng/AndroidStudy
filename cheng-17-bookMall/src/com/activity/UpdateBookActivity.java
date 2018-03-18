package com.activity;

import com.Interface.UserInterface;
import com.InterfaceImp.UserInterfaceImp;
import com.bean.User;
import com.example.cheng_17_bookmall.R;
import com.example.cheng_17_bookmall.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class UpdateBookActivity extends Activity {

	EditText etUpdateName,etUpdateAuthor;
	Button btUpdateUser,btUpdateCancel;
	UserInterface ui;
	int userid;
	User user=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_book);
		init();
		
		//3.从intent对象中获得要修改书籍的关键字userid中重新取出要修改的书籍，并在界面上显示
		userid=this.getIntent().getIntExtra("userid",-10000);
		//4.根据关键字userid从数据库表user
		ui=new UserInterfaceImp(getApplicationContext());
		user=ui.getUserById(userid);
		if(user!=null){
			etUpdateName.setText(user.getUserName());
			etUpdateAuthor.setText(user.getAuthor());
		}else{
			Toast.makeText(getApplicationContext(), "书籍不存在",
					Toast.LENGTH_SHORT).show();
		}
		//5.定义注册事件监听器
		btUpdateUser.setOnClickListener(new UpdateUser());
	}

	public class UpdateUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//6.获得用户信息
			String name=etUpdateName.getText().toString().trim();
			String author=etUpdateAuthor.getText().toString().trim();
			if(name==null||name.equals("")||author==null||author.equals("")){
				Toast.makeText(getApplicationContext(), "书籍信息不完整", Toast.LENGTH_SHORT).show();
			}else{
				//7.把数据封装到User对象中
				User user=new User();
				user.setUserid(userid);
				user.setUserName(name);
				user.setAuthor(author);
				//8.把User对象写到（保存）数据库book的user表中
				ui=new UserInterfaceImp(getApplicationContext());
				if(ui.update(user)!=0){
					Toast.makeText(getApplicationContext(), "修改成功",
							Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(UpdateBookActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "修改不成功",
							Toast.LENGTH_SHORT).show();
					etUpdateName.requestFocus();
				}
			}
		}
		
	}
	private void init() {
		// TODO Auto-generated method stub
		etUpdateName=(EditText)this.findViewById(R.id.etUpdateName);
		etUpdateAuthor=(EditText)this.findViewById(R.id.etUpdateAuthor);
		btUpdateUser=(Button)this.findViewById(R.id.btUpdateUser);
		btUpdateCancel=(Button)this.findViewById(R.id.btUpdateCancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_book, menu);
		return true;
	}

}
