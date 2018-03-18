package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//1.�����Ա����
	ListView lv;
	ArrayList<Student> al=new ArrayList<Student>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		final String[]names={"һë","��ë","��ë","��ë","��ë"};
		final String[]ids={"201526702001","201526702002","201526702003","201526702004","201526702005"};
		final int []images={R.drawable.iv1,R.drawable.iv2,R.drawable.iv3,R.drawable.iv4,R.drawable.iv5};
		
		for(int i=0;i<images.length;i++)
		{
			al.add(new Student(images[i],names[i],ids[i]));
		}
		MyAdapter ma=new MyAdapter(getApplicationContext(),al,images,names,ids);
        lv.setAdapter(ma);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
			    
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
        	    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        	    builder.setTitle("ѧ����ϢΪ��");
        	    builder.setIcon(images[arg2]);
        	    builder.setMessage(al.get(arg2).showMessage());
        	    builder.setPositiveButton("ȷ��", null);
    			builder.setNegativeButton("ȡ��",null);
    			AlertDialog ad=builder.create();
    			ad.show();

			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
