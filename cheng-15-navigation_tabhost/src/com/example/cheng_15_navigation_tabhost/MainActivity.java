package com.example.cheng_15_navigation_tabhost;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

public class MainActivity extends Activity {

	ViewPager viewPager;
	ArrayList<View> pageViews;
	// ��������ͼƬLinearLayout
	ViewGroup main;
	// ����СԲ���LinearLayout
	ViewGroup group;
	TextView []dots;
	Button btStart;
	int colorActive=Color.parseColor("#FFE4E1");
	int colorInactive=Color.WHITE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		LayoutInflater inflater=getLayoutInflater();
		
		pageViews=new ArrayList<View>();
		View view =inflater.inflate(R.layout.item03, null);
		pageViews.add(inflater.inflate(R.layout.item01, null));
		pageViews.add(inflater.inflate(R.layout.item02, null));
		pageViews.add(view);
		btStart=(Button)view.findViewById(R.id.start);
		btStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, TabHostActivity.class);
				startActivity(intent);
			}
		});
		
		main=(ViewGroup)inflater.inflate(R.layout.activity_main,null);
		group=(ViewGroup)main.findViewById(R.id.viewGroup);
		viewPager=(ViewPager)main.findViewById(R.id.guidePages);

		
		dots = new TextView[pageViews.size()];
		for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));//Բ��
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive);  
            
            if (i == 0) {  
                //Ĭ��ѡ�е�һ��ͼƬ
            	dots[i].setTextColor(colorActive);    
            } else {  
            	dots[i].setTextColor(colorInactive);  
            } 
            group.addView(dots[i]);
        }

		setContentView(main);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
		
	} 
	class GuidePageAdapter extends PagerAdapter {  
	  	  
        @Override  
        public int getCount() {  //�˺���������������baseAdapter�е�getCount()����
            return pageViews.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(pageViews.get(arg1));  
        }  
  
        @Override  //�˺���������������baseAdapter�е�getView()����
        public Object instantiateItem(View arg0, int arg1) {  //arg0����Viewpager����
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).addView(pageViews.get(arg1));  
            return pageViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
    } 
    // ָ��ҳ������¼�������
    class GuidePageChangeListener implements OnPageChangeListener {  
    	  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        
	    public void onPageSelected(int arg0) {	
        	for (int i = 0; i < dots.length; i++) {  
        		dots[arg0].setTextColor(colorActive);       
                if (arg0 != i) {  
                	dots[i].setTextColor(colorInactive);
               }
	        }
        }

	   public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		  getMenuInflater().inflate(R.menu.main, menu);
		  return true;
	}
  }
}

