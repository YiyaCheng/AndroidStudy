package com.example.test_download02;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.EncodingUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etUrl;
	Button btDown;
	TextView tvContent;
	File file=null;
	byte[] bt;
	URL textUrl=null;
	String urlstr=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		btDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")){
					Toast.makeText(getApplicationContext(), "������ͼƬ��ַ"
							, Toast.LENGTH_SHORT).show();
				}else{
					/*
					 * AsyncTask��һ���������ͺ�execute()��doInBackground()�еĲ�������һ��
					 * AsyncTask�������������ͺ�doInBackground()�ķ�������һ�²��Һ�
					 * onPostExecute()�еĲ�������һ��
					 */
					new AsyncTask<String,Void,byte[]>(){

						@Override
						protected byte[] doInBackground(String... params) {
							// TODO Auto-generated method stub
							try {
								//(1)��������
								textUrl=new URL(params[0]);
								HttpURLConnection con=(HttpURLConnection)textUrl.openConnection();
								
								//�������ӵ��������
								con.setRequestMethod("GET");
								con.setRequestProperty("connection","keep-alive");
								//con.setReadTimeout(5000);
								con.setDoInput(true);
								
								
								//(2)�ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								if(code==200){
									InputStream is=con.getInputStream();
									//bt=new byte[is.available()];
									byte[] bt=new byte[con.getContentLength()];
									is.read(bt);
									/*tvContent.setText(new String(bt));
									
									String fileContent=tvContent.getText().toString().trim();
									file=new File(Environment.getExternalStorageDirectory(),"txt1");
									FileOutputStream fout=new FileOutputStream(file);
									fout.write(fileContent.getBytes("utf-8"));
									fout.close();*/
									is.close();
									
								}else{
									Toast.makeText(getApplicationContext(), 
											"�������Ӳ��ɹ����ļ�������!"
											, Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return bt;
						}

						@Override
						protected void onPostExecute(byte[] result) {
							// TODO Auto-generated method stub
							
							String temp=EncodingUtils.getString(bt,"gb2312");
							tvContent.setText(temp);
							//tvContent.setText(new String(result));
						}
					}.execute(urlstr);;
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUrl=(EditText)this.findViewById(R.id.et_url);
		btDown=(Button)this.findViewById(R.id.bt_down);
		tvContent=(TextView)this.findViewById(R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}