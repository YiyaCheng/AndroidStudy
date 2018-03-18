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
	//1.定义变量
	EditText etUrl;
	Button btDown;
	ImageView iv;
	Bitmap bitmap=null;
	URL imageUrl=null;
	String urlstr=null;
	//(5).定义Handler对象
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
		//2.初始化
		init();
		//3.定义、注册事件监听器
		btDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.在androidmainfest.xml中增加访问网络权限
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")){
					Toast.makeText(getApplicationContext(), "请输入图片地址"
							, Toast.LENGTH_SHORT).show();
				}else{
					//5.定义线程下载图片
					new Thread(){
						public void run(){
							
							try {
								//(1)连接网络
								imageUrl=new URL(urlstr);
								HttpURLConnection con=(HttpURLConnection)imageUrl
										.openConnection();
								//设置连接的相关属性
								con.setRequestMethod("GET");
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//可以下载网络上的数据
								con.setDoOutput(true);//可以上传数据到网络
								con.setReadTimeout(5000);
								
								//(2)判断连接是否成功
								int code=con.getResponseCode();
								if(code==200){
									//(3).Inputstream对象读取网络上的数据
									InputStream is=con.getInputStream();
								/*
									byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();
									*/
									bitmap=BitmapFactory.decodeStream(is);
								}else{
									Toast.makeText(getApplicationContext(), 
											"网络连接不成功或图片不存在!"
											, Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//(4).下载完成后，通过Handle对象通知主线程来更新imageView
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
