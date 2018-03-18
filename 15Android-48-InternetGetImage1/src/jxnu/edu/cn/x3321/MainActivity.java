package jxnu.edu.cn.x3321;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�������
	EditText etUrl;
	Button btDown;
	ImageView iv;
	Bitmap bitmap=null;
	URL imageUrl=null;
	String urlstr=null;
	//(5).����Handler����
	Handler hd=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0){
				iv.setImageBitmap(bitmap);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.���塢ע���¼�������
		btDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.��androidmainfest.xml�����ӷ�������Ȩ��
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")){
					Toast.makeText(getApplicationContext(), "������ͼƬ��ַ"
							, Toast.LENGTH_SHORT).show();
				}else{
					//5.�����߳�����ͼƬ
					new Thread(){
						public void run(){
							
							try {
								//(1)��������
								imageUrl=new URL(urlstr);
								HttpURLConnection con=(HttpURLConnection)imageUrl
										.openConnection();
								//�������ӵ��������
								con.setRequestMethod("GET");
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//�������������ϵ�����
								con.setDoOutput(true);//�����ϴ����ݵ�����
								con.setReadTimeout(5000);
								
								//(2)�ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								if(code==200){
									//(3).Inputstream�����ȡ�����ϵ�����
									InputStream is=con.getInputStream();
								/*
									byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();
									*/
									bitmap=BitmapFactory.decodeStream(is);
								}else{
									Toast.makeText(getApplicationContext(), 
											"�������Ӳ��ɹ���ͼƬ������!"
											, Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//(4).������ɺ�ͨ��Handle����֪ͨ���߳�������imageView
							Message msg=new Message();
							msg.what=0;
							hd.sendMessage(msg);
						}
					}.start();
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUrl=(EditText)this.findViewById(R.id.et_url);
		btDown=(Button)this.findViewById(R.id.bt_Down);
		iv=(ImageView)this.findViewById(R.id.iv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
