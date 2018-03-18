package com.InterfaceImp;

import java.util.ArrayList;
import java.util.HashMap;

import com.Interface.UserInterface;
import com.bean.User;
import com.db.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserInterfaceImp implements UserInterface {
	public static SQLiteDatabase dbInstance;
	Context context;

	public UserInterfaceImp(Context context) {		
		this.context = context;
		dbInstance=DatabaseHelper.openDatabase(context);
	}
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		int bl=0;
		String  sql="insert into user (userName,author)"
				+ " values ('"+user.getUserName()+"','"+user.getAuthor()+"')";
		dbInstance.execSQL(sql);
		bl=1;
		return bl;
	}
	@Override
	public ArrayList<HashMap<String, Object>> getAllUsers() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=
				new ArrayList<HashMap<String, Object>>();
		String sql="select * from user";
		Cursor cursor=null;
		cursor=dbInstance.rawQuery(sql, null);
		//moveToNext移动光标到下一行
		while(cursor.moveToNext()){
			HashMap<String, Object> user=new HashMap<String, Object>();
			user.put("userid",cursor.getInt(
					cursor.getColumnIndex("userid")));
			user.put("name",cursor.getString(
					cursor.getColumnIndex("userName")));
			user.put("author",cursor.getString(
					cursor.getColumnIndex("author")));
			user.put("imageName",cursor.getString(
					cursor.getColumnIndex("imageName")));
			list.add(user);
		}
		return list;
	}
	@Override
	public int deleteById(int userid) {
		// TODO Auto-generated method stub
		int bl=0;
		//数值型不需要单引号
		String  sql="delete from user where userid="+userid;
		dbInstance.execSQL(sql);
		bl=1;
		return bl;
	}
	@Override
	public User getUserById(int userid) {
		// TODO Auto-generated method stub
		User user=null;
		String sql="select * from user where userid="+userid;
		Cursor cursor=null;
		cursor=dbInstance.rawQuery(sql, null);
		while(cursor.moveToNext()){
			user=new User();
			user.setUserid(cursor.getInt(
					cursor.getColumnIndex("userid")));
			user.setUserName(cursor.getString(
					cursor.getColumnIndex("userName")));
			user.setAuthor(cursor.getString(
					cursor.getColumnIndex("author")));
			user.setImageName(cursor.getString(
					cursor.getColumnIndex("imageName")));
		}
		return user;
	}
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		int bl=0;
		String sql="update user set userName='"+user.getUserName()
				+"',author='"+user.getAuthor()+"'where userid="
				+user.getUserid();
		bl=1;
		return bl;
	}
	@Override
	public ArrayList<HashMap<String, Object>> getUsersByName(String name) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=
				 new ArrayList<HashMap<String, Object>>();
		String sql="select * from user where username like '%"+name+"%'";
	    Cursor cursor=null;
		cursor=dbInstance.rawQuery(sql, null);
		//moveToNext移动光标到下一行
		while(cursor.moveToNext()){
			HashMap<String, Object> user=new HashMap<String, Object>();
			user.put("userid",cursor.getInt(
					cursor.getColumnIndex("userid")));
			user.put("name",cursor.getString(
					cursor.getColumnIndex("userName")));
			user.put("author",cursor.getString(
					cursor.getColumnIndex("author")));
			user.put("imageName",cursor.getString(
					cursor.getColumnIndex("imageName")));
			list.add(user);
		}
		return list;
	}

}
