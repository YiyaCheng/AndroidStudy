package com.example.listview;
import java.util.ArrayList;  
 import android.app.Activity;  
 import android.app.AlertDialog;  
 import android.content.DialogInterface;  
 import android.os.Bundle;  
 import android.view.View;  
 import android.widget.AdapterView;  
 import android.widget.AdapterView.OnItemClickListener;  
 import android.widget.ArrayAdapter;  
 import android.widget.ListView;  
/*  
 * @author lingdududu  
  * 该程序的主要功能是点击ListView的学生姓名，就能弹出对话框  
  * 在对话框里面显示学生的详细信息：图片，姓名，性别，年龄  
 */ 
 public class ListViewActivity extends Activity {  
     private ListView lv;  
     @Override 
     public void onCreate(Bundle savedInstanceState) {  
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.activity_main);  
           
         //学生图片的ID数组  
         final int[] stu_pic = {  
                    R.drawable.pic1,  
                    R.drawable.pic2,  
                    R.drawable.pic3,  
                    R.drawable.pic4,  
                    R.drawable.pic5,  
                    R.drawable.pic6};  
           
         final AlertDialog.Builder builder = new AlertDialog.Builder(this);  
           
         lv=(ListView)findViewById(R.id.list);  
           
         /*生成动态数组，加入数据*/ 
         final ArrayList<Student>students = new ArrayList<Student>();  
          
         students.add(new Student("小张","女",21));  
         students.add(new Student("小明","男",22));  
         students.add(new Student("小王","男",23));  
         students.add(new Student("小丽","女",21));  
         students.add(new Student("小红","女",22));  
         students.add(new Student("小周","男",23));  
           
                 
         ArrayAdapter<Student> adapter = new ArrayAdapter<Student>  
                (this,//布局文件   
                 android.R.layout.simple_list_item_1,//android.R.layout.simple_list_item_1，系统定义的布局文件   
                 students);//数据来源    
        //为ListView设置适配器  
        lv.setAdapter(adapter);  
        lv.setOnItemClickListener(new OnItemClickListener() {  
              
            //arg0  发生点击动作的AdapterView  
            //arg1 在AdapterView中被点击的视图(它是由adapter提供的一个视图)  
            //arg2 视图在adapter中的位置  
            //arg3 被点击元素的行id  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3){  
            //将学生的详细信息在对话框里面显示  
            builder.setMessage(students.get(arg2).getStuIfo())  
            //显示学生的图片  
            .setIcon(stu_pic[arg2])  
            .setTitle("你好，这是我的详细信息！")             
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int which) {  
                      
                }         
            });  
            //创建对话框  
            AlertDialog ad = builder.create();  
            //显示对话框  
            ad.show();  
            }  
        });       
   }  
} 
