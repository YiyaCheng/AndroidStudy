package com.example.cheng_16_txtreader;

import android.os.*;
import android.os.Environment;

import java.io.File;
import java.util.*;

import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.*;

public class BookListActivity extends ListActivity {

	ListView bookList;
	List<File> fileList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		
		bookList=(ListView)this.findViewById(R.id.booklist);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File path = Environment.getExternalStorageDirectory();
			File[] f = path.listFiles();
			fill(f);
		}
	}

	private void fill(File[] files) {
		// TODO Auto-generated method stub
		fileList = new ArrayList<File>();
		
		//文件列表遍历
		for (File file : files) {
			if (isValidFileOrDir(file)) {
				fileList.add(file);
			}
		}
		ArrayAdapter<String> fileNameList = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,fileToStrArr(fileList));
		
		//设置ListActivity界面的方法 
		//同setContentView在Activity界面中的作用
		setListAdapter(fileNameList);
	}

	private String[] fileToStrArr(List<File> fl) {
		// TODO Auto-generated method stub
		ArrayList<String> fnList = new ArrayList<String>();
		for (int i = 0; i < fl.size(); i++) {
			String nameString = fl.get(i).getName();
			fnList.add(nameString);
		}
		return fnList.toArray(new String[0]);
	}

	private boolean isValidFileOrDir(File file) {
		// TODO Auto-generated method stub
		if (file.isDirectory()) {
			return true;
		}
		else {
			//将文件名变为小写重新存入新字符串
			String fileNameLow = file.getName().toLowerCase();
			if (fileNameLow.endsWith(".txt")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list, menu);
		return true;
	}

}
