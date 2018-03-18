package com.example.android_03_slidenmenu;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnTouchListener {
	public static final int SNAP_SPEED=150;		//滑动速度
	private int screenWidth;	//屏幕宽度
	private int leftEdge;		//向左滑动的最大位移，marginLeft到达此值之后，不能再减少
	private int rightEdge=0;	//menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。
	private int menuPadding=100;	//menu完全显示时，留给content的宽度值
	private View content;		//主内容的布局
	private View menu;			//menu的布局
	private LinearLayout.LayoutParams menuParams;	//menu布局的参数，通过此参数来更改leftMargin的值。
	private float xDown;		//记录手指按下时的横坐标
	private float xMove;		//记录手指移动时的横坐标
	private float xUp;			//记录手指抬起时的横坐标
	private boolean isMenuVisible;	//menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
	private VelocityTracker vt;	//用于计算手指的滑动速度

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		content.setOnTouchListener(this);
	}

	private void init() {
		// TODO Auto-generated method stub

		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
		screenWidth = window.getDefaultDisplay().getWidth();  	//获得屏幕宽度
		content = findViewById(R.id.content);  
		menu = findViewById(R.id.menu);  
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();  
		menuParams.width = screenWidth - menuPadding;		// 将menu的宽度设置为屏幕宽度减去menuPadding 
		leftEdge = -menuParams.width;  			// 左边缘的值赋值为menu宽度的负数
		menuParams.leftMargin = leftEdge;  		// menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
		content.getLayoutParams().width = screenWidth; 		// 将content的宽度设置为屏幕宽度  
	}
	public boolean onTouch(View v,MotionEvent event){
		createVelocityTracker(event);
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			xDown=event.getRawX();	//记录手指按下时的横坐标
			break;
		case MotionEvent.ACTION_MOVE :
			xMove=event.getRawX();
			int distanceX=(int)(xMove-xDown);
			if(isMenuVisible){
				menuParams.leftMargin=distanceX;						
			}
			else{
				menuParams.leftMargin=leftEdge+distanceX;
			}
			if(menuParams.leftMargin<leftEdge){
				menuParams.leftMargin=leftEdge;				//leftEdge为负,设置最大位移
			}else if(menuParams.leftMargin>rightEdge){		//rightEdge=0
				menuParams.leftMargin=rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP :
			// 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面
			xUp=event.getRawX();
			if(wantToShowMenu()){
				if(shouldScrollToMenu()){
					scrollToMenu();
				}else{
					scrollToContent();
				}
			}else if(wantToShowContent()){
				if(shouldScrollToContent()){
					scrollToContent();
				}else{
					scrollToMenu();
				}
			}
			recycleVelocityTracker();
			break;
		}
		return true;
	}
	private boolean wantToShowContent(){
		return xUp-xDown<0&&isMenuVisible;		//正显示menu且左划了
	}
	private boolean wantToShowMenu(){
		return xUp-xDown>0&& !isMenuVisible;	//正显示content且右划了
	}
	private boolean shouldScrollToMenu(){
		//滑动距离大于一半屏幕宽度或者滑动速度足够
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED;
	}
	private boolean shouldScrollToContent(){
		//如果手指移动距离加上menuPadding大于屏幕的1/2， 
		//或者手指移动速度大于SNAP_VELOCITY， 就认为应该滚动将content展示出来。
		return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED; 
	}
	//将屏幕滚动到menu界面，滚动速度设定为30
	private void scrollToMenu(){
		new ScrollTask().execute(30);
	}
	//将屏幕滚动到content界面，滚动速度设定为-30
	private void scrollToContent(){
		new ScrollTask().execute(-30);
	}
	//创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中
	private void createVelocityTracker(MotionEvent event) {  
        if (vt == null) {  
            vt = VelocityTracker.obtain();  
        }  
        vt.addMovement(event);  
    }  
	//获取手指在content页面的滑动速度
	private int getScrollVelocity() {
		// TODO Auto-generated method stub
		vt.computeCurrentVelocity(1000);	//以1000毫秒滑动多少像素为单位
		int velocity=(int)vt.getXVelocity();
		return Math.abs(velocity);
	}
	//回收VelocityTracker对象
    private void recycleVelocityTracker() {  
        vt.recycle();  
        vt = null;  
    } 
    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {  
  
        @Override  
        protected Integer doInBackground(Integer... speed) {  
            int leftMargin = menuParams.leftMargin;  
            // 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。  
            while (true) {  
                leftMargin = leftMargin + speed[0];  
                if (leftMargin > rightEdge) {  
                    leftMargin = rightEdge;  
                    break;  
                }  
                if (leftMargin < leftEdge) {  
                    leftMargin = leftEdge;  
                    break;  
                }  
                publishProgress(leftMargin);  
                // 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。  
                sleep(20);  
            }  
            if (speed[0] > 0) {  
                isMenuVisible = true;  
            } else {  
                isMenuVisible = false;  
            }  
            return leftMargin;  
        }  
  
        @Override  
        protected void onProgressUpdate(Integer... leftMargin) {  
            menuParams.leftMargin = leftMargin[0];  
            menu.setLayoutParams(menuParams);  
        }  
  
        @Override  
        protected void onPostExecute(Integer leftMargin) {  
            menuParams.leftMargin = leftMargin;  
            menu.setLayoutParams(menuParams);  
        }  
    } 
    //指定当前线程睡眠多久，以毫秒为单位
    private void sleep(long millis) {  
        try {  
            Thread.sleep(millis);  
           } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    } 
}
