package com.example.weather02;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import com.example.weather02.bean.CityWeather;

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
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {

	Spinner spProvince,spCity;
	ArrayList<String> provinceset=new ArrayList<String>();
	ArrayList<String> cityset=new ArrayList<String>();
	TextView tvFirstDay,tvSecondDay,tvThirdDay,tvCurrent;
	ImageView firstImg1,firstImg2;
	ImageView secondImg1,secondImg2;
	ImageView thirdImg1,thirdImg2;
	ArrayList<String> provinceList;
	Bitmap bitmap=null;
	URL imageUrl=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		//获取省份列表
		String provinceWs="http://ws.webXml.com.cn/"
				+ "WebServices/WeatherWS.asmx/getRegionProvince";
		new AsyncTask<String, Void,ArrayList<String>>(){
			@Override
			protected ArrayList<String> doInBackground(String... params) {
				// TODO Auto-generated method stub
				//ProvinceName provinceName=null;
				ArrayList<String> provinceList=new ArrayList<String>();
				try {
					
					URL url=new URL(params[0]);
					System.out.println("url:"+url);
					HttpURLConnection con=(HttpURLConnection)url.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("connection","keep-alive");
					con.setDoInput(true);
					
					int code=con.getResponseCode();
					System.out.println("code:"+code);
					if(code==200){
						InputStream is=con.getInputStream();
						XmlPullParser xp=Xml.newPullParser();
						xp.setInput(is,"utf-8");
						int type=xp.getEventType();
						//int index=1;
						while(type!=xp.END_DOCUMENT){
							if(type==xp.START_TAG){
								if(xp.getName().equals("string")){
									//provinceName.setProvinceName(xp.nextText());
									provinceList.add(xp.nextText());
								}
							}
							type=xp.next();
						}
						is.close();
						con.disconnect();
					}else{
						Toast.makeText(getApplicationContext(),"网络连接不成功或资源不存在!",Toast.LENGTH_SHORT).show();
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return provinceList;
			}

			@Override
			protected void onPostExecute(ArrayList<String> result) {
				// TODO Auto-generated method stub
				provinceset.addAll(result);
				final ArrayList<String> allProvince=new ArrayList<String>();
				for(int i=0;i<provinceset.size();i++)
				   allProvince.add(provinceset.get(i).split(",")[0]);
				//Toast.makeText(getApplicationContext(), provinceset.toString(),Toast.LENGTH_LONG).show();
				MyAdapter ma=new MyAdapter(getApplicationContext(),allProvince);
	        	spProvince.setAdapter(ma);

	        	spProvince.setOnItemSelectedListener(new OnItemSelectedListener() {

	        		@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
						//获取城市列表
	        			   TextView tv=(TextView)spProvince.getChildAt(0);
	        			   String provinceName=tv.getText().toString().trim();	
						   String cityWs="http://ws.webXml.com.cn/"
					              + "WebServices/WeatherWS.asmx/getSupportCityString?theRegionCode=${provinceName}";
			                cityWs=cityWs.replace("${provinceName}", provinceName);
			                new AsyncTask<String, Void,ArrayList<String>>(){
			                @Override
			                protected ArrayList<String> doInBackground(String... params) {
			                	// TODO Auto-generated method stub
			                	ArrayList<String> cityList=new ArrayList<String>();
			                	try {
			                		URL url=new URL(params[0]);
			                		System.out.println("url:"+url);
			                		HttpURLConnection con=(HttpURLConnection)url.openConnection();
			                		con.setRequestMethod("GET");
			                		con.setRequestProperty("connection","keep-alive");
			                		con.setDoInput(true);
						
			                		int code=con.getResponseCode();
			                		System.out.println("code:"+code);
			                		if(code==200){
			                			InputStream is=con.getInputStream();
			                			XmlPullParser xp=Xml.newPullParser();
			                			xp.setInput(is,"utf-8");
			                			int type=xp.getEventType();
			                			while(type!=xp.END_DOCUMENT){
			                				if(type==xp.START_TAG){
			                					if(xp.getName().equals("string")){
			                						cityList.add(xp.nextText());
			                					}
			                				}
			                				type=xp.next();
			                			}
			                			is.close();
			                			con.disconnect();
			                		}else{
			                			Toast.makeText(getApplicationContext(),"网络连接不成功或资源不存在!",Toast.LENGTH_SHORT).show();
			                		}
						
			                	} catch (Exception e) {
			                		// TODO Auto-generated catch block
			                		e.printStackTrace();
			                	}
			                	return cityList;
			                }
			                
			                protected void onPostExecute(ArrayList<String> result) {
			                	// TODO Auto-generated method stub
			                	cityset.clear();
			                	cityset.addAll(result);
			                	ArrayList<String> allCity=new ArrayList<String>();
			                	for(int i=0;i<cityset.size();i++)
			                		allCity.add(cityset.get(i).split(",")[0]);
			                	//Toast.makeText(getApplicationContext(), provinceset.toString(),Toast.LENGTH_SHORT).show();
			                	MyAdapter maCity=new MyAdapter(getApplicationContext(),allCity);
			                	spCity.setAdapter(maCity);
			                	
			                	
			                	//获取天气信息
			                	spCity.setOnItemSelectedListener(new OnItemSelectedListener() {

									@Override
									public void onItemSelected(
											AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
										TextView ctv=(TextView)spCity.getChildAt(0);
					        			String cityName=ctv.getText().toString().trim();	
					        			String Ws="http://ws.webxml.com.cn/"
												+ "WebServices/WeatherWS.asmx/getWeather";
										String params="theCityCode="+cityName+"&theUserID=";
										new AsyncTask<String, Void, CityWeather>() {

											@Override
											protected CityWeather doInBackground(String... params) {
												// TODO Auto-generated method stub
												CityWeather  cityWeather=null;
												
												try {
													//(3)连接网络
													System.out.println("ws:"+params[0]);
													System.out.println("params:"+params[1]);
													byte[] bt=params[1].getBytes("utf-8");
													
													URL url=new URL(params[0]);							
													HttpURLConnection con=
															(HttpURLConnection)url.openConnection();
													//(4)设置属性
													con.setRequestMethod("POST");
													
													con.setRequestProperty("Content-Length", ""+bt.length);
													//设置请求的内容类型
													con.setRequestProperty("Content-Type", 
															"application/x-www-form-urlencoded");
												
													con.setRequestProperty("connection", "keep-alive");
													con.setDoInput(true);//可以下载网络上的数据	
											
													con.setReadTimeout(5000);
													
													//通过OutputStream对象把参数传给webService
													OutputStream out=con.getOutputStream();
													out.write(bt);								
													
													//(5)判断连接是否成功
													int code=con.getResponseCode();
													System.out.println("code:"+code);
													if(code==200){
														cityWeather=new CityWeather();
														//(6).Inputstream对象读取网络上的数据
														InputStream is=con.getInputStream();
														//(7).XmlPullParser对象解析is,得到天气信息
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
																	if(index==5){
																		cityWeather.setTodayWeather(xp.nextText());
																	}else if(index==13){//第一天
																		cityWeather.setFirstDate(xp.nextText());
																	}else if(index==14){
																		cityWeather.setFirstTem(xp.nextText());
																	}else if(index==15){
																		cityWeather.setFirstWind(xp.nextText());
																	}else if(index==16){
																		cityWeather.setImg1(xp.nextText());
																	}else if(index==17){
																		cityWeather.setImg2(xp.nextText());
																	}else if(index==18){//第二天
																		cityWeather.setSecondDate(xp.nextText());
																	}else if(index==19){
																		cityWeather.setSecondTem(xp.nextText());
																	}else if(index==20){
																		cityWeather.setSecondWind(xp.nextText());
																	}else if(index==21){
																		cityWeather.setImg3(xp.nextText());
																	}else if(index==22){
																		cityWeather.setImg4(xp.nextText());
																	}else if(index==23){//第三天
																		cityWeather.setThirdDate(xp.nextText());
																	}else if(index==24){
																		cityWeather.setThirdTem(xp.nextText());
																	}else if(index==25){
																		cityWeather.setThirdWind(xp.nextText());
																	}else if(index==26){
																		cityWeather.setImg3(xp.nextText());
																	}else if(index==27){
																		cityWeather.setImg4(xp.nextText());
																	}
																	index++;
																}							
																
															}
															if(index>27){
																break;
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
												
												return cityWeather;
											}

											@Override
											protected void onPostExecute(CityWeather cw) {
												// TODO Auto-generated method stub
												//(8).把天气信息写到textView上
												String htmlStr1="<font>"
														+cw.getFirstDate()
														+"</font><br>"
											        +"<font>"
										                +cw.getFirstTem()
										            +"</font><br>"
													+"<font>"
									                     +cw.getFirstWind()
									                +"</font><br>";
												tvFirstDay.setText(Html.fromHtml(htmlStr1));
												
												String htmlStr2="<font>"
														+cw.getSecondDate()
														+"</font><br>"
												        +"<font>"
											                +cw.getSecondTem()
											            +"</font><br>"
														+"<font>"
										                     +cw.getSecondWind()
										                +"</font><br>";
												tvSecondDay.setText(Html.fromHtml(htmlStr2));
												
												String htmlStr3="<font>"
														+cw.getThirdDate()
														+"</font><br>"
												        +"<font>"
											                +cw.getThirdTem()
											            +"</font><br>"
														+"<font>"
										                     +cw.getThirdWind()
										                +"</font><br>";
												tvThirdDay.setText(Html.fromHtml(htmlStr3));
												tvCurrent.setText(cw.getTodayWeather());
												//(9)在androidManfiest.xml中增加访问网络权限
												String img1Name=cw.getImg1();
												String img2Name=cw.getImg2();
												String img3Name=cw.getImg3();
												String img4Name=cw.getImg4();
												String img5Name=cw.getImg5();
												String img6Name=cw.getImg6();
												showImage(img1Name,firstImg1);
												showImage(img2Name,firstImg2);
												showImage(img3Name,secondImg1);
												showImage(img4Name,secondImg2);
												showImage(img5Name,thirdImg1);
												showImage(img6Name,thirdImg2);
											}

											private void showImage(
													String imgName,
													final ImageView img) {
												
												String dowUrl="http://www.webxml.com.cn/images/weather/"+imgName;
												//取得缓存路径
										        File file=getCacheDir();//得到手机上的缓存对象,可以作为一个路径
										        //判断缓存目录中是否存在image2Name图片
										        //File fileName=new File(file,imgName);//真正的文件名
										        /*if(fileName.exists()){
										        	img.setImageBitmap(BitmapFactory.decodeFile(fileName.getAbsolutePath()));
										        }
										        else{*/
										        	new AsyncTask<String,Void,Bitmap>(){
													@Override
													protected Bitmap doInBackground(String... params) {
														// TODO Auto-generated method stub
														try {
															//(1)连接网络
															imageUrl=new URL(params[0]);
															HttpURLConnection con=(HttpURLConnection)imageUrl
																	.openConnection();
															//设置连接的相关属性
															con.setRequestMethod("GET");
															con.setRequestProperty("connection", "keep-alive");
															con.setDoInput(true);//可以下载网络上的数据
															con.setReadTimeout(5000);
															
															//(2)判断连接是否成功
															int code=con.getResponseCode();
															if(code==200){
																//(3).Inputstream对象读取网络上的数据
																InputStream is=con.getInputStream();
																bitmap=BitmapFactory.decodeStream(is);
																
																/*写到缓存中去*/
															}else{
																Toast.makeText(getApplicationContext(), 
																		"网络连接不成功或图片不存在!"
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
														img.setImageBitmap(result);
													}						
													
												}.execute(dowUrl);
										       //}
											}
								
										}.execute(Ws,params);
									}

									@Override
									public void onNothingSelected(
											AdapterView<?> arg0) {
										// TODO Auto-generated method stub
										
									}
								});
			                }	
			                }.execute(cityWs);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}

					
				});
			}	

		}.execute(provinceWs);
		
}	
		
		


	private void init() {
		// TODO Auto-generated method stub
		spProvince=(Spinner)this.findViewById(R.id.province);
		spCity=(Spinner)this.findViewById(R.id.city);
		firstImg1=(ImageView)this.findViewById(R.id.firstday_img1);
		firstImg2=(ImageView)this.findViewById(R.id.firstday_img2);
		secondImg1=(ImageView)this.findViewById(R.id.secondday_img1);
		secondImg2=(ImageView)this.findViewById(R.id.secondday_img2);
		thirdImg1=(ImageView)this.findViewById(R.id.thirdday_img1);
		thirdImg2=(ImageView)this.findViewById(R.id.thirdday_img2);
		tvFirstDay=(TextView)this.findViewById(R.id.firstday_tv);
		tvSecondDay=(TextView)this.findViewById(R.id.secondday_tv);
		tvThirdDay=(TextView)this.findViewById(R.id.thirdday_tv);
		tvCurrent=(TextView)this.findViewById(R.id.current_tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
