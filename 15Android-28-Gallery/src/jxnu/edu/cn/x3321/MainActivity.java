package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�������
	ImageView iv;
	Gallery gl;
	int imageIDs[]={R.drawable.a,R.drawable.b,
			R.drawable.c,R.drawable.d,
			R.drawable.e,R.drawable.f,
			R.drawable.g};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2��ʼ��
		init();
		//3.��ͼƬ��װ��adapter��
		MyAdapter ma=new MyAdapter();
		//4.��ma�е����ݼ��ص�Gallery
		gl.setAdapter(ma);
		//5.���塢ע���¼�������
		gl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				iv.setImageResource(imageIDs[arg2%imageIDs.length]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imageIDs[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView iv=new ImageView(getApplication());
			iv.setImageResource(imageIDs[position%imageIDs.length]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(100,60));
			
			return iv;
		}
		
	}

	private void init() {
		// TODO Auto-generated method stub
		iv=(ImageView)this.findViewById(R.id.iv);
		gl=(Gallery)this.findViewById(R.id.gl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
