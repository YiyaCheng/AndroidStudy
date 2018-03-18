package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String DBName="book";
	static final int Version=1;
	public static SQLiteDatabase dbInstance;
	/*context:�����Ķ���
	 * name:���������ʵ����ݿ���
	 * factory:�α깤���࣬����Ϊnull
	 * version:���ݿ�İ汾��,������>0������
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		//�������ݿ⣬ֻҪ���ݿ����Ͱ汾�Ų��䣬��ֻ�ᴴ��һ�����ݿ�
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		
	}

	/*
	 * �ڴ������ݿ�֮���Զ�ִ��oncreate��������������ֻ�ᴴ��һ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String sql="create table user(userid integer primary key autoincrement,"
				+"userName varchar(20),author text,imageName text)";
		db.execSQL(sql);
	}

	/*
	 * �ڴ������ݿ�����version�����仯�����Զ�ִ��onUpgrade�������������޸ı�
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
