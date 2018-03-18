package jxnu.edu.cn.x3321;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义变量
	EditText etPhone;
	Button btSearch;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.定义注册事件监听器
		btSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.在androidManfiest.xml中增加访问网络权限
				String phone=etPhone.getText().toString().trim();
				if(phone==null||phone.equals("")){
					Toast.makeText(getApplicationContext(), "请输入电话号码!",
							Toast.LENGTH_SHORT).show();
				}else{
					//定义访问webService的URL
					String ws="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"
							+ "/getMobileCodeInfo?mobileCode=${mobile}&userID=";
					ws=ws.replace("${mobile}", phone);
					
					System.out.println("ws:"+ws);
					//5.启动异步任务现下载图片
					/*
					 * AsyncTask第一个参数类型和execute()、doInBackground()中的参数类型一致
					 * AsyncTask第三个参数类型和doInBackground()的返回类型一致并且和
					 * onPostExecute()中的参数类型一致
					 */
                     new AsyncTask<String,Void,String>(){

						@Override
						protected String doInBackground(String... params) {
							// TODO Auto-generated method stub
							String phoneNumber="";
							try {
								//(1)连接网络
								System.out.println("params:"+params[0]);
								URL url=new URL(params[0]);
								HttpURLConnection con=(HttpURLConnection)url
										.openConnection();
								//设置连接的相关属性
								con.setRequestMethod("GET");
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//可以下载网络上的数据
							
								con.setReadTimeout(5000);
								
								//(2)判断连接是否成功
								int code=con.getResponseCode();
								System.out.println("code:"+code);
								if(code==200){
									//(3).Inputstream对象读取网络上的数据
									InputStream is=con.getInputStream();
								    //(4)XmlPullParser对象解析is,得到mobile对应的所在地
									XmlPullParser xp=Xml.newPullParser();
									xp.setInput(is, "utf-8");
									int type=xp.getEventType();
									while(type!=xp.END_DOCUMENT){
										System.out.println("DOCUMENT:");
										if(type==xp.START_TAG){
											if(xp.getName().equals("string")){
												phoneNumber=xp.nextText();
												System.out.println("phoneNumber:"+phoneNumber);
												break;
											}
										}
										type=xp.next();
									}
									is.close();
									con.disconnect();
									
								}else{
									/*
									Toast.makeText(getApplicationContext(), 
											"网络连接不成功或资源不存在!"
											, Toast.LENGTH_SHORT).show();
											*/
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return phoneNumber;
						}

						@Override
						protected void onPostExecute(String result) {
							// TODO Auto-generated method stub
							System.out.println("phoneNumber:"+result);
							tv.setText(result);
						}						
						
					}.execute(ws);
				}
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etPhone=(EditText)this.findViewById(R.id.et_phone);
		btSearch=(Button)this.findViewById(R.id.bt_search);
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
