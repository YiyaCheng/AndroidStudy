package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String DBName="book";
	static final int Version=1;
	public static SQLiteDatabase dbInstance;
	/*context:上下文对象
	 * name:创建、访问的数据库名
	 * factory:游标工厂类，可以为null
	 * version:数据库的版本号,必须是>0的整数
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		//创建数据库，只要数据库名和版本号不变，它只会创建一次数据库
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		
	}

	/*
	 * 在创建数据库之后将自动执行oncreate方法来创建表，它只会创建一次
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String sql="create table user(userid integer primary key autoincrement,"
				+"userName varchar(20),author text,imageName text)";
		db.execSQL(sql);
	}

	/*
	 * 在创建数据库后，如果version发生变化，将自动执行onUpgrade方法来创建或修改表
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public static SQLiteDatabase openDatabase(Context context) {
		// TODO Auto-generated method stub
		if(dbInstance==null){
			DatabaseHelper db=new DatabaseHelper(context,DBName,null,Version);
			dbInstance=db.getWritableDatabase();
		}
		return dbInstance;

	}
}
