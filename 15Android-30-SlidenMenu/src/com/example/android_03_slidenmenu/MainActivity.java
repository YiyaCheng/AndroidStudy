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
	public static final int SNAP_SPEED=150;		//�����ٶ�
	private int screenWidth;	//��Ļ���
	private int leftEdge;		//���󻬶������λ�ƣ�marginLeft�����ֵ֮�󣬲����ټ���
	private int rightEdge=0;	//menu�����Ի��������ұ�Ե��ֵ��Ϊ0����marginLeft����0֮�󣬲������ӡ�
	private int menuPadding=100;	//menu��ȫ��ʾʱ������content�Ŀ��ֵ
	private View content;		//�����ݵĲ���
	private View menu;			//menu�Ĳ���
	private LinearLayout.LayoutParams menuParams;	//menu���ֵĲ�����ͨ���˲���������leftMargin��ֵ��
	private float xDown;		//��¼��ָ����ʱ�ĺ�����
	private float xMove;		//��¼��ָ�ƶ�ʱ�ĺ�����
	private float xUp;			//��¼��ָ̧��ʱ�ĺ�����
	private boolean isMenuVisible;	//menu��ǰ����ʾ�������ء�ֻ����ȫ��ʾ������menuʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч��
	private VelocityTracker vt;	//���ڼ�����ָ�Ļ����ٶ�

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
		screenWidth = window.getDefaultDisplay().getWidth();  	//�����Ļ���
		content = findViewById(R.id.content);  
		menu = findViewById(R.id.menu);  
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();  
		menuParams.width = screenWidth - menuPadding;		// ��menu�Ŀ������Ϊ��Ļ��ȼ�ȥmenuPadding 
		leftEdge = -menuParams.width;  			// ���Ե��ֵ��ֵΪmenu��ȵĸ���
		menuParams.leftMargin = leftEdge;  		// menu��leftMargin����Ϊ���Ե��ֵ��������ʼ��ʱmenu�ͱ�Ϊ���ɼ�
		content.getLayoutParams().width = screenWidth; 		// ��content�Ŀ������Ϊ��Ļ���  
	}
	public boolean onTouch(View v,MotionEvent event){
		createVelocityTracker(event);
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			xDown=event.getRawX();	//��¼��ָ����ʱ�ĺ�����
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
				menuParams.leftMargin=leftEdge;				//leftEdgeΪ��,�������λ��
			}else if(menuParams.leftMargin>rightEdge){		//rightEdge=0
				menuParams.leftMargin=rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP :
			// ��ָ̧��ʱ�������жϵ�ǰ���Ƶ���ͼ���Ӷ������ǹ�����menu���棬���ǹ�����content����
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
		return xUp-xDown<0&&isMenuVisible;		//����ʾmenu������
	}
	private boolean wantToShowMenu(){
		return xUp-xDown>0&& !isMenuVisible;	//����ʾcontent���һ���
	}
	private boolean shouldScrollToMenu(){
		//�����������һ����Ļ��Ȼ��߻����ٶ��㹻
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED;
	}
	private boolean shouldScrollToContent(){
		//�����ָ�ƶ��������menuPadding������Ļ��1/2�� 
		//������ָ�ƶ��ٶȴ���SNAP_VELOCITY�� ����ΪӦ�ù�����contentչʾ������
		return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_SPEED; 
	}
	//����Ļ������menu���棬�����ٶ��趨Ϊ30
	private void scrollToMenu(){
		new ScrollTask().execute(30);
	}
	//����Ļ������content���棬�����ٶ��趨Ϊ-30
	private void scrollToContent(){
		new ScrollTask().execute(-30);
	}
	//����VelocityTracker���󣬲�������content����Ļ����¼����뵽VelocityTracker����
	private void createVelocityTracker(MotionEvent event) {  
        if (vt == null) {  
            vt = VelocityTracker.obtain();  
        }  
        vt.addMovement(event);  
    }  
	//��ȡ��ָ��contentҳ��Ļ����ٶ�
	private int getScrollVelocity() {
		// TODO Auto-generated method stub
		vt.computeCurrentVelocity(1000);	//��1000���뻬����������Ϊ��λ
		int velocity=(int)vt.getXVelocity();
		return Math.abs(velocity);
	}
	//����VelocityTracker����
    private void recycleVelocityTracker() {  
        vt.recycle();  
        vt = null;  
    } 
    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {  
  
        @Override  
        protected Integer doInBackground(Integer... speed) {  
            int leftMargin = menuParams.leftMargin;  
            // ���ݴ�����ٶ����������棬������������߽���ұ߽�ʱ������ѭ����  
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
                // Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��20���룬�������۲��ܹ���������������  
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
    //ָ����ǰ�߳�˯�߶�ã��Ժ���Ϊ��λ
    private void sleep(long millis) {  
        try {  
            Thread.sleep(millis);  
           } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    } 
}
