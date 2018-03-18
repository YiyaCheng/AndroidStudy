package jxnu.edu.cn.x3321;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
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
					//5.��androidManifest.xml���ӷ���SDCardȨ��
					//6.���SDCard�Ƿ����
					if(Environment.getExternalStorageState()
							.equals(Environment.MEDIA_MOUNTED)){
						File file=new File(Environment.getExternalStorageDirectory()
								,fileName);
						//7. ͨ��FileOutputStream ���������д���ֻ���SDCard(mnt/SDCard/files)
						try {
							FileOutputStream fout=new FileOutputStream(file);
							fout.write(fileContent.getBytes("utf-8"));
							fout.close();
							Toast.makeText(getApplicationContext(),
									"�ļ�����SDCard�ɹ�!",Toast.LENGTH_SHORT)
									.show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(),
									"�ļ�����SDCard���ɹ�!",Toast.LENGTH_SHORT)
									.show();
							e.printStackTrace();
						}
					}else
					{
						Toast.makeText(getApplicationContext(),
								"SDCard������!",Toast.LENGTH_SHORT)
								.show();
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
					//5.��androidManifest.xml���ӷ���SDCardȨ��
					//6.���SDCard�Ƿ����
					if(Environment.getExternalStorageState()
							.equals(Environment.MEDIA_MOUNTED)){
						File file=new File(Environment.getExternalStorageDirectory()
								,fileName);
						//7. ͨ��FileInputStream ��������ݴ��ֻ���SDCard��������
						try {
							FileInputStream fin=new FileInputStream(file);
							byte[] bt=new byte[fin.available()];
							fin.read(bt);
							etFileConent.setText(new String(bt));
							Toast.makeText(getApplicationContext(),
									"���ݴ�SDCard��ȡ�ɹ�!",Toast.LENGTH_SHORT)
									.show();
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(),
									"���ݴ�SDCard��ȡ���ɹ�!",Toast.LENGTH_SHORT)
									.show();
							e.printStackTrace();
						}
						
					}else{
						Toast.makeText(getApplicationContext(),
								"SDCard������!",Toast.LENGTH_SHORT)
								.show();
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
