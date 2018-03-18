package com.example.test_searchip;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {

	EditText etIP;
	Button btSearch;
	TextView tvShow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		btSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ip=etIP.getText().toString().trim();
				if(ip==null||ip.equals("")){
					Toast.makeText(getApplicationContext(), "������ip��ַ",
							Toast.LENGTH_SHORT).show();
				}else{
					String ws="http://ws.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx"
							+ "/getCountryCityByIp?theIpAddress=${IP}";
					ws=ws.replace("${IP}", ip);
					//System.out.println("ws:"+ws);
					
					new AsyncTask<String,Void,String>(){
						protected String doInBackground(String... params) {
							String ipAddress="";
							try {
								//(1)��������
								//System.out.println("params:"+params[0]);
								URL url=new URL(params[0]);
								HttpURLConnection con=(HttpURLConnection)url
										.openConnection();
								//�������ӵ��������
								con.setRequestMethod("GET");
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//�������������ϵ�����
								//con.setReadTimeout(5000);
								
								//(2)�ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								//System.out.println("code:"+code);
								if(code==200){
									//(3).Inputstream�����ȡ�����ϵ�����
									InputStream is=con.getInputStream();
								    //(4)XmlPullParser�������is,�õ�mobile��Ӧ�����ڵ�
									XmlPullParser xp=Xml.newPullParser();
									xp.setInput(is, "utf-8");
									int type=xp.getEventType();
									int index=1;
									while(type!=xp.END_DOCUMENT){
										//System.out.println("DOCUMENT:");
										if(type==xp.START_TAG){
											if(xp.getName().equals("string")){
												if(index==2){
												ipAddress=xp.nextText();
												//System.out.println("ipAddress:"+ipAddress);
												}
												index++;
											}	
										}
										if(index>2)
					                        break;
										type=xp.next();
									}
									is.close();
									con.disconnect();
									
								}else{					
									Toast.makeText(getApplicationContext(), 
											"�������Ӳ��ɹ�����Դ������!"
											, Toast.LENGTH_SHORT).show();		
								}		
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return ipAddress;
						}

						protected void onPostExecute(String result) {
							// TODO Auto-generated method stub
							//System.out.println("ipAddress:"+result);
							tvShow.setText("IP��ַ��ϢΪ��"+result);
						}

					}.execute(ws);
						
				}
			}
		});	
	}

	private void init() {
		// TODO Auto-generated method stub
		etIP=(EditText)this.findViewById(R.id.et_IP);
		btSearch=(Button)this.findViewById(R.id.bt_search);
		tvShow=(TextView)this.findViewById(R.id.tv_show);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
