package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1定义成员变量
	ImageView iv;
	Button btAmplify,btReduce,btLeftRoat,btRightRoat;
	Bitmap bmp;
	int size=100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.生成Bitmap对象并放到imageView中,设置图片的透明的
		bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list_1);
		iv.setImageBitmap(bmp);
		iv.setAlpha(size);
		//4.定义、注册事件监听器
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				size=255;
				iv.setAlpha(size);
			}
		});
		btAmplify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,1.2f);
				iv.setImageBitmap(bmp);
			}
		});
		btReduce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,0.8f);
				iv.setImageBitmap(bmp);
			}
		});
		btLeftRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=RoateToFit(bmp,-90f);
				iv.setImageBitmap(bmp);
			}
		});
        btRightRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=RoateToFit(bmp,90f);
				iv.setImageBitmap(bmp);
			}
		});
	}

	protected Bitmap RoateToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postRotate(f);
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0,0, width, height,matrix,true);
		return bmResult;
	}

	protected Bitmap scaleToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postScale(f, f);
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0,0, width, height,matrix,true);
		return bmResult;
	}

	private void init() {
		// TODO Auto-generated method stub
		iv=(ImageView)this.findViewById(R.id.iv);
		btAmplify=(Button)this.findViewById(R.id.bt_amplify);
		btReduce=(Button)this.findViewById(R.id.bt_reduce);
		btLeftRoat=(Button)this.findViewById(R.id.bt_leftRoate);
		btRightRoat=(Button)this.findViewById(R.id.bt_rightRoate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
