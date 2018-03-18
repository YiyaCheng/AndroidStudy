package jxnu.edu.cn.x3321.contentProvide;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;

public class MyContentProvide extends ContentProvider {

	//1.为conentProvide定义一个唯一的标识(URI),方便其他应用通过该标识能访问到MyContentProvide
	private static String auth="jnxu.edu.cn.x3321";
	//2.把conentProvide定义一个唯一的标识(URI)配置到androidMainfest.xml
	//3.定义Uri匹配器   常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
	private static UriMatcher um=new UriMatcher(UriMatcher.NO_MATCH);
	UserInterface ui;
	static{
		um.addURI(auth, "all",1);//添加需要匹配uri，如果匹配就会返回匹配码1
		um.addURI(auth, "#",2);
		um.addURI(auth, "insert", 3);
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		ui=new UserInterfaceImp(getContext());
		Cursor cursor=null;
		switch(um.match(uri)){
		case 3://insert
					
			
		
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		ui=new UserInterfaceImp(getContext());
		Cursor cursor=null;
		switch(um.match(uri)){
		case 1:
			cursor=UserInterfaceImp.dbInstance.
			query("user", new String[]{"userid","userName",
					"phone","imageName"}, null, null, null, null, null);
			break;
			
		case 2:
			//content://jxnu.edu.cn.x3321/2   ContentUris类用于操作Uri路径后面的ID部分
			long id=ContentUris.parseId(uri);
			if(selection==null||selection.equals("")){
				selection="userid="+id;
			}else{
				selection=selection+"  and userid="+id;
			}
			cursor=UserInterfaceImp.dbInstance.
					query("user", null, selection, null, null, null, null);
					break;
		
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
