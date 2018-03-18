package jxnu.edu.cn.x3321;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.*;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {
	
	ListView lv;
	ArrayList<Student> student= new ArrayList<Student>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		Student stu1=new Student(R.drawable.iv1,"一毛","201526702001");
		Student stu2=new Student(R.drawable.iv2,"二毛","201526702002");
		Student stu3=new Student(R.drawable.iv3,"三毛","201526702003");
		Student stu4=new Student(R.drawable.iv4,"四毛","201526702004");
		Student stu5=new Student(R.drawable.iv5,"五毛","201526702005");
		Student stu6=new Student(R.drawable.iv6,"六毛","201526702006");
		
		student.add(stu1);
		student.add(stu2);
		student.add(stu3);
		student.add(stu4);
		student.add(stu5);
		student.add(stu6);
		
		ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Student s:student){
             Map<String,Object> map=new HashMap<String,Object>();
             map.put("image", s.getImage());
             map.put("name", s.getName());
             map.put("id",s.getId());
             list.add(map);
		}
		
		SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),
				list,R.layout.table,new String[]{"image","name","id"},
				new int[]{R.id.iv,R.id.tv_Name,R.id.tv_ID});
		lv.setAdapter(sa);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
			    
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
        	    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        	    builder.setTitle("学生信息为：");
        	    builder.setMessage(student.get(arg2).showMessage());
        	    builder.setPositiveButton("确定", null);
    			builder.setNegativeButton("取消",null);
    			AlertDialog ad=builder.create();
    			ad.show();

			}
		});
	}
	
	private void init(){
		
		lv=(ListView)this.findViewById(R.id.lv);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
