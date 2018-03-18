package jxnu.edu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
//1.定义访问图片的数组
	int[] images=new int[]{
			R.drawable.list1,
			R.drawable.list2,
			R.drawable.list3,
			R.drawable.list4
	};
	int currentImage=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.获得id=ll的线性布局
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.ll);
		//创建imageView组件
		final ImageView imge=new ImageView(MainActivity.this);
		imge.setImageResource(images[currentImage]);
		//将imageView组件添加到id=ll的线性布局中
		layout.addView(imge);
		//注册事件监听器
		imge.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 改变imageView中的图片
				currentImage=(currentImage+1)%images.length;
				imge.setImageResource(images[currentImage]);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
