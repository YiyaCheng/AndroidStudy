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
	//1.�������
	EditText etPhone;
	Button btSearch;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.����ע���¼�������
		btSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.��androidManfiest.xml�����ӷ�������Ȩ��
				String phone=etPhone.getText().toString().trim();
				if(phone==null||phone.equals("")){
					Toast.makeText(getApplicationContext(), "������绰����!",
							Toast.LENGTH_SHORT).show();
				}else{
					//�������webService��URL
					String ws="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"
							+ "/getMobileCodeInfo?mobileCode=${mobile}&userID=";
					ws=ws.replace("${mobile}", phone);
					
					System.out.println("ws:"+ws);
					//5.�����첽����������ͼƬ
					/*
					 * AsyncTask��һ���������ͺ�execute()��doInBackground()�еĲ�������һ��
					 * AsyncTask�������������ͺ�doInBackground()�ķ�������һ�²��Һ�
					 * onPostExecute()�еĲ�������һ��
					 */
                     new AsyncTask<String,Void,String>(){

						@Override
						protected String doInBackground(String... params) {
							// TODO Auto-generated method stub
							String phoneNumber="";
							try {
								//(1)��������
								System.out.println("params:"+params[0]);
								URL url=new URL(params[0]);
								HttpURLConnection con=(HttpURLConnection)url
										.openConnection();
								//�������ӵ��������
								con.setRequestMethod("GET");
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//�������������ϵ�����
							
								con.setReadTimeout(5000);
								
								//(2)�ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								System.out.println("code:"+code);
								if(code==200){
									//(3).Inputstream�����ȡ�����ϵ�����
									InputStream is=con.getInputStream();
								    //(4)XmlPullParser�������is,�õ�mobile��Ӧ�����ڵ�
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
											"�������Ӳ��ɹ�����Դ������!"
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
