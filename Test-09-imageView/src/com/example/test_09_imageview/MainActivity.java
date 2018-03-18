package com.example.test_09_imageview;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Menu;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {
	//1�����Ա����
	//ImageView iv;
	Button btAmplify,btReduce,btLeftRoat,btRightRoat;
	Bitmap bmp;
	int size=100;
	
	int[] images=new int[]{
			R.drawable.list1,
			R.drawable.list2,
			R.drawable.list3,
			R.drawable.list4
	};
	int currentImage=0;
	int num=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//2.���id=ll�����Բ���
				LinearLayout layout=(LinearLayout)this.findViewById(R.id.ll);
				//����imageView���
				final ImageView imge=new ImageView(MainActivity.this);
				imge.setImageResource(images[currentImage]);
				//��imageView�����ӵ�id=ll�����Բ�����
				layout.addView(imge);
				//ע���¼�������
				bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list1);
				imge.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// �ı�imageView�е�ͼƬ
						currentImage=(currentImage+1)%images.length;
						imge.setImageResource(images[currentImage]);
						
						bmp=BitmapFactory.decodeResource(getResources(),images[currentImage]);
					}
				});
		
		
		imge.setImageBitmap(bmp);
		
		btAmplify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,1.2f);
				imge.setImageBitmap(bmp);
			}
		});
		btReduce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,0.8f);
				imge.setImageBitmap(bmp);
			}
		});
		btLeftRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				bmp=RoateToFit(bmp,-90f);
				imge.setImageBitmap(bmp);
			}
		});
        btRightRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				bmp=RoateToFit(bmp,90f);
				imge.setImageBitmap(bmp);
			}
		});
	}

	protected Bitmap RoateToFit(Bitmap bmp2, float f) {
		
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postRotate(f);
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0,0, width, height,matrix,true);
		return bmResult;
	}

	protected Bitmap scaleToFit(Bitmap bmp2, float f) {
		
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postScale(f, f);
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0,0, width, height,matrix,true);
		return bmResult;
	}

	private void init() {
		// TODO Auto-generated method stub
        
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
