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
	//1.�������
	EditText etFileName,etFileConent;
	Button btSave,btRead,btClean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		inint();
		//3.����ע���¼�������
		btSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.����ļ������ļ�����
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileConent.getText().toString().trim();
				if(fileName==null||fileName.equals("")){
					Toast.makeText(getApplicationContext(),
							"�������ļ���!",Toast.LENGTH_SHORT)
							.show();
				}else{
					//5. FileOutputStream ���������д���ֻ����ļ���(data/data/projectPackage/files)
					try {
						FileOutputStream fout=getApplication()
								.openFileOutput(fileName, MODE_PRIVATE);						
						fout.write(fileContent.getBytes("utf-8"));
						fout.close();
						Toast.makeText(getApplicationContext(),
								"�ļ�����ɹ�!",Toast.LENGTH_SHORT)
								.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(),
								"�ļ����治�ɹ�!",Toast.LENGTH_SHORT)
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
				//4.����ļ���
				String fileName=etFileName.getText().toString().trim();
				if(fileName==null||fileName.equals("")){
					Toast.makeText(getApplicationContext(),
							"�������ļ���!",Toast.LENGTH_SHORT)
							.show();
				}else{
					//ͨ��FileInputStream��������ݴ��ֻ����ļ��ж�������
					try {
						FileInputStream fin=getApplication()
								.openFileInput(fileName);
						byte []bt=new byte[fin.available()];
						fin.read(bt);
						fin.close();
						etFileConent.setText(new String(bt));
						Toast.makeText(getApplicationContext(),
								"�ļ���ȡ�ɹ�!",Toast.LENGTH_SHORT)
								.show();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(),
								"�ļ���ȡ���ɹ�!",Toast.LENGTH_SHORT)
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
