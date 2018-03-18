package com.activity;

import android.os.Bundle;

import com.Interface.UserInterface;
import com.InterfaceImp.UserInterfaceImp;
import com.bean.User;
import com.example.cheng_17_bookmall.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class AddBookActivity extends Activity {

	EditText etAddName,etAuthor;
	Button btAddUser,btAddCancel;
	UserInterface ui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book);
		
		init();
		//专门定义一个类去实现监听
		btAddUser.setOnClickListener(new AddUser());
	}

	public class AddUser implements View.OnClickListener{
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//获得用户信息
			String name=etAddName.getText().toString().trim();
			String author=etAuthor.getText().toString().trim();
			if(name==null||name.equals("")||author==null||author.equals("")){
				Toast.makeText(getApplicationContext(), "书籍信息不完整", Toast.LENGTH_SHORT).show();
			}else{
				//把数据封装到User对象中
				User user=new User();
				user.setUserName(name);
				user.setAuthor(author);
				//把User对象写到（保存）数据库contact的user表中
				ui=new UserInterfaceImp(getApplicationContext());
				//ui.insert(user);
				if(ui.insert(user)==1)
				{
					Toast.makeText(getApplicationContext(), "书籍信息保存成功",
							Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(AddBookActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}
	  
	}
	private void init() {
		// TODO Auto-generated method stub
		etAddName=(EditText)this.findViewById(R.id.etAddName);
		etAuthor=(EditText)this.findViewById(R.id.etAuthor);
		btAddUser=(Button)this.findViewById(R.id.btAddUser);
		btAddCancel=(Button)this.findViewById(R.id.btAddCancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_book, menu);
		return true;
	}

}
