package com.example.test_searchpostcode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import com.example.test_searchpostcode.bean.PostCode;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	EditText etPostProvince,etPostCity;
	Button btSearch;
	TextView tv1,tv2,tv3,tv4;
	String postCode;
	//URL url=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		btSearch.setOnClickListener(new Search());
	}

	public class Search implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String province=etPostProvince.getText().toString().trim();
			String city=etPostCity.getText().toString().trim();
			if(province==null||province.equals("")||city==null||city.equals("")){
				Toast.makeText(getApplicationContext(), 
						"请输入地址信息!", Toast.LENGTH_SHORT)
						.show();					
			}
			else{
				String codeWs="http://ws.webXml.com.cn/"
						+ "WebServices/ChinaZipSearchWebService.asmx/"
						+ "getZipCodeByAddress?theProvinceName="+province+"&theCityName="+city+"&theAddress="+"&userID=";
				//String params="theZipCode="+province+"&userID=";
				new AsyncTask<String,Void,String>(){

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						postCode="";
						try {
							URL url=new URL(params[0]);
							HttpURLConnection con=(HttpURLConnection)url.openConnection();
							con.setRequestMethod("GET");
							con.setRequestProperty("connection", "keep-alive");
							con.setDoInput(true);	
							
							int code=con.getResponseCode();
							if(code==200){
				
								InputStream is=con.getInputStream();
								XmlPullParser xp=Xml.newPullParser();
								xp.setInput(is,"utf-8");
								int type=xp.getEventType();
								
								while(type!=xp.END_DOCUMENT){
									if(type==xp.START_TAG){
										if(xp.getName().equals("ZIP")){
											postCode=xp.nextText();
											break;
										}
									}
									type=xp.next();	
								}
								is.close();
							    con.disconnect();
							}else{
								Toast.makeText(getApplicationContext(), 
										"网络连接不成功或资源不存在!"
										, Toast.LENGTH_SHORT).show();
							}
							
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return postCode;
					}

					@Override
					protected void onPostExecute(String result) {
						// TODO Auto-generated method stub
						tv1.setText("该地邮编为："+result);
					}
					
					
				}.execute(codeWs);
				
			}
		}
		
	}
	private void init() {
		// TODO Auto-generated method stub
		etPostProvince=(EditText)this.findViewById(R.id.et_postProvince);
		etPostCity=(EditText)this.findViewById(R.id.et_postCity);
		btSearch=(Button)this.findViewById(R.id.bt_search);
		tv1=(TextView)this.findViewById(R.id.tv1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
