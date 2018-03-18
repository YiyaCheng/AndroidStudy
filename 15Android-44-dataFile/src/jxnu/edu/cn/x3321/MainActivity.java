package jxnu.edu.cn.x3321;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.定义变量
	EditText etFileName,etFileConent;
	Button btSave,btRead,btClean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		inint();
		//3.定义注册事件监听器
		btSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.获得文件名和文件内容
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileConent.getText().toString().trim();
				if(fileName==null||fileName.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入文件名!",Toast.LENGTH_SHORT)
							.show();
				}else{
					//5. FileOutputStream 对象把数据写到手机的文件中(data/data/projectPackage/files)
					try {
						FileOutputStream fout=getApplication()
								.openFileOutput(fileName, MODE_PRIVATE);						
						fout.write(fileContent.getBytes("utf-8"));
						fout.close();
						Toast.makeText(getApplicationContext(),
								"文件保存成功!",Toast.LENGTH_SHORT)
								.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(),
								"文件保存不成功!",Toast.LENGTH_SHORT)
								.show();
						e.printStackTrace();
					}
				}
			}
		});
		btClean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etFileName.setText("");
				etFileConent.setText("");
			}
		});
		
		btRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.获得文件名
				String fileName=etFileName.getText().toString().trim();
				if(fileName==null||fileName.equals("")){
					Toast.makeText(getApplicationContext(),
							"请输入文件名!",Toast.LENGTH_SHORT)
							.show();
				}else{
					//通过FileInputStream对象把数据从手机的文件中读到程序
					try {
						FileInputStream fin=getApplication()
								.openFileInput(fileName);
						byte []bt=new byte[fin.available()];
						fin.read(bt);
						fin.close();
						etFileConent.setText(new String(bt));
						Toast.makeText(getApplicationContext(),
								"文件读取成功!",Toast.LENGTH_SHORT)
								.show();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(),
								"文件读取不成功!",Toast.LENGTH_SHORT)
								.show();
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void inint() {
		// TODO Auto-generated method stub
		etFileName=(EditText)this.findViewById(R.id.et_fileName);
		etFileConent=(EditText)this.findViewById(R.id.et_fileConent);
		btSave=(Button)this.findViewById(R.id.bt_save);
		btRead=(Button)this.findViewById(R.id.bt_read);
		btClean=(Button)this.findViewById(R.id.bt_clean);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
