package jxnu.edu.cn.x3321;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import jxnu.edu.cn.x3321.bean.CityWeather;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�������
	EditText etCity;
	Button btSearch;
	ImageView img1,img2;
	TextView tv;

	Bitmap bitmap=null;
	URL imageUrl1=null;
	URL imageUrl2=null;
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
				//(1)�������ĳ�������
				String cityName=etCity.getText().toString().trim();
				//cityName="�ϲ�";
				if(cityName==null||cityName.equals("")){
					Toast.makeText(getApplicationContext(), 
							"�������������!", Toast.LENGTH_SHORT)
							.show();					
				}else{
					//(2)�������webService��URL
					String cityWs="http://ws.webxml.com.cn/"
							+ "WebServices/WeatherWS.asmx/getWeather";
					String params="theCityCode="+cityName+"&theUserID=";
					new AsyncTask<String, Void, CityWeather>() {

						@Override
						protected CityWeather doInBackground(String... params) {
							// TODO Auto-generated method stub
							CityWeather  cityWeather=null;
							
							try {
								//(3)��������
								System.out.println("ws:"+params[0]);
								System.out.println("params:"+params[1]);
								byte[] bt=params[1].getBytes("utf-8");
								
								URL url=new URL(params[0]);							
								HttpURLConnection con=
										(HttpURLConnection)url.openConnection();
								//(4)��������
								con.setRequestMethod("POST");
								
								con.setRequestProperty("Content-Length", ""+bt.length);
								//�����������������
								con.setRequestProperty("Content-Type", 
										"application/x-www-form-urlencoded");
							
								con.setRequestProperty("connection", "keep-alive");
								con.setDoInput(true);//�������������ϵ�����	
						
								//con.setReadTimeout(5000);
								
								//ͨ��OutputStream����Ѳ�������webService
								OutputStream out=con.getOutputStream();
								out.write(bt);								
								
								//(5)�ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								System.out.println("code:"+code);
								if(code==200){
									cityWeather=new CityWeather();
									//(6).Inputstream�����ȡ�����ϵ�����
									InputStream is=con.getInputStream();
									//(7).XmlPullParser�������is,�õ�������Ϣ
									System.out.println("is:"+is);
									XmlPullParser xp=Xml.newPullParser();
									xp.setInput(is, "utf-8");
									int type=xp.getEventType();
									int index=1;
									while(type!=XmlPullParser.END_DOCUMENT){
										System.out.println("type:"+type);
										if(type==XmlPullParser.START_TAG){
											if(xp.getName().equals("string")){
												System.out.println("index:"+index);
												if(index==1){
													cityWeather.setCityName(xp.nextText());
												}else if(index==5){
													cityWeather.setWeather(xp.nextText());
												}else if(index==7){
													cityWeather.setSum(xp.nextText());
												}else if(index==8){
													cityWeather.setDate(xp.nextText());
												}else if(index==9){
													cityWeather.setCap(xp.nextText());
												}else if(index==11){
													cityWeather.setImg1(xp.nextText());
												}else if(index==12){
													cityWeather.setImg1(xp.nextText());
												}
												index++;
											}							
											
										}
										if(index>12){
											break;
										}
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
							
							return cityWeather;
						}

						@Override
						protected void onPostExecute(CityWeather cw) {
							// TODO Auto-generated method stub
							//(8).��������Ϣд��textView��
							String htmlStr="<font color='red'>"
									            +cw.getCityName()
									        +"</font><br>"
									        +"<font color='blue'>"
								                +cw.getWeather()
								            +"</font><br>"
											+"<font color='black'>"
							                     +cw.getDate()
							                +"</font><br>"
							                +"<font color='#458B00'>"
							                     +cw.getSum()
							                +"</font><br>"
							                +"<font color='green'>"
							                     +cw.getCap()
							                +"</font><br>";
							tv.setText(Html.fromHtml(htmlStr));
							//(9)��androidManfiest.xml�����ӷ�������Ȩ��
							
							String img1Name=cw.getImg1();
							String img2Name=cw.getImg2();
							showImage1(img1Name,img1);
							showImage2(img2Name,img2);
						}

						
						
						
					}.execute(cityWs,params);

				}
			}
		});
	}
	private void showImage2(String img2Name, final ImageView img2) {
		// TODO Auto-generated method stub
        String dowUrl="http://www.webxml.com.cn/images/weather/"+img2Name;
		
		new AsyncTask<String,Void,Bitmap>(){

			@Override
			protected Bitmap doInBackground(String... params) {
				// TODO Auto-generated method stub
				try {
					//(1)��������
					imageUrl2=new URL(params[0]);
					HttpURLConnection con=(HttpURLConnection)imageUrl2
							.openConnection();
					//�������ӵ��������
					con.setRequestMethod("GET");
					con.setRequestProperty("connection", "keep-alive");
					con.setDoInput(true);//�������������ϵ�����
					//con.setReadTimeout(5000);
					
					//(2)�ж������Ƿ�ɹ�
					int code=con.getResponseCode();
					if(code==200){
						//(3).Inputstream�����ȡ�����ϵ�����
						InputStream is=con.getInputStream();
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
				
				return bitmap;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				// TODO Auto-generated method stub
				img2.setImageBitmap(result);
			}						
			
		}.execute(dowUrl);
	}

	private void showImage1(String img1Name, final ImageView img1) {
		// TODO Auto-generated method stub
		String dowUrl="http://www.webxml.com.cn/images/weather/"+img1Name;
		
		new AsyncTask<String,Void,Bitmap>(){

			@Override
			protected Bitmap doInBackground(String... params) {
				// TODO Auto-generated method stub
				try {
					//(1)��������
					imageUrl1=new URL(params[0]);
					HttpURLConnection con=(HttpURLConnection)imageUrl1
							.openConnection();
					//�������ӵ��������
					con.setRequestMethod("GET");
					con.setRequestProperty("connection", "keep-alive");
					con.setDoInput(true);//�������������ϵ�����
					//con.setReadTimeout(5000);
					
					//(2)�ж������Ƿ�ɹ�
					int code=con.getResponseCode();
					if(code==200){
						//(3).Inputstream�����ȡ�����ϵ�����
						InputStream is=con.getInputStream();
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
				
				return bitmap;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				// TODO Auto-generated method stub
				img1.setImageBitmap(result);
			}						
			
		}.execute(dowUrl);
	}
	private void init() {
		// TODO Auto-generated method stub
		etCity=(EditText)this.findViewById(R.id.et_cityName);
		btSearch=(Button)this.findViewById(R.id.bt_search);
		img1=(ImageView)this.findViewById(R.id.iv1);
		img2=(ImageView)this.findViewById(R.id.iv2);
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
