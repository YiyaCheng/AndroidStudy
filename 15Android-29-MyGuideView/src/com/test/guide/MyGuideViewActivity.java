package com.test.guide;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/**
 * Android实现左右滑动指引效果
 * @Description: Android实现左右滑动指引效果

 * @File: MyGuideViewActivity.java

 * @Package com.test.guide

 * @Author 

 * @Date 

 * @Version V1.0
 */
public class MyGuideViewActivity extends Activity {
	 private ViewPager viewPager;  
	 private ArrayList<View> pageViews;  
	 private ImageView imageView;  
	 private ImageView[] imageViews; 
	 // 包裹滑动图片LinearLayout
	 private ViewGroup main;
	 // 包裹小圆点的LinearLayout
	 private ViewGroup group;
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置无标题窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        LayoutInflater inflater = getLayoutInflater(); 
        //LayoutInflater这个类的作用类似于findViewById()。不同点是LayoutInflater是用来找res/layout/
        //下的xml布局文件，并且实例化；而findViewById()是找xml布局文件下的具体widget控件
        //(如Button、TextView等)。
        pageViews = new ArrayList<View>();  //把item1-06.xml放入pageViews数组中，以方便下面把pageViews中的数据
                                            //封装到GuidePageAdapter适配器中。
        pageViews.add(inflater.inflate(R.layout.item05, null));
        pageViews.add(inflater.inflate(R.layout.item06, null));
        pageViews.add(inflater.inflate(R.layout.item01, null));  
        pageViews.add(inflater.inflate(R.layout.item02, null));  
        pageViews.add(inflater.inflate(R.layout.item03, null));  
        pageViews.add(inflater.inflate(R.layout.item04, null));          
        
        
        main = (ViewGroup)inflater.inflate(R.layout.main, null); //把main.xml加载进来 
        
        group = (ViewGroup)main.findViewById(R.id.viewGroup);  
        viewPager = (ViewPager)main.findViewById(R.id.guidePages);  
        
        imageViews = new ImageView[pageViews.size()];  
        for (int i = 0; i < pageViews.size(); i++) {  // 把小圆点图片加载到LinearLayout中。
            imageView = new ImageView(MyGuideViewActivity.this);  
            imageView.setLayoutParams(new LayoutParams(20,20));  
            imageView.setPadding(20, 0, 20, 0);  
            imageViews[i] = imageView;  
            
            if (i == 0) {  
                //默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            } else {  
                imageViews[i].setBackgroundResource(R.drawable.page_indicator);  
            }  
            
            group.addView(imageViews[i]);  
        }  
        
        setContentView(main);
        
        viewPager.setAdapter(new GuidePageAdapter());  //把适配器中的数据加载到viewPager中。
        viewPager.setOnPageChangeListener(new GuidePageChangeListener()); //当滑动中间的图片，对应的小圆点也要滑动
    }
    
    // 指引页面数据适配器
    class GuidePageAdapter extends PagerAdapter {  
  	  
        @Override  
        public int getCount() {  //此函数的作用类似于baseAdapter中的getCount()函数
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
  
        @Override  //此函数的作用类似于baseAdapter中的getView()函数
        public Object instantiateItem(View arg0, int arg1) {  //arg0就是Viewpager对象
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
    
    // 指引页面更改事件监听器
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
            for (int i = 0; i < imageViews.length; i++) {  
                imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
                
                if (arg0 != i) {  
                    imageViews[i].setBackgroundResource(R.drawable.page_indicator);  
                }  
            }
        }  
    }  
}