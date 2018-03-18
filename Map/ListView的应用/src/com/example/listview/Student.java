package com.example.listview;
public class Student {  

    public String sname;  
    public String ssex;  
    public int sage;  
      
    Student(String name,String sex,int age){  
        sname=name;  
        ssex=sex;  
        sage=age;  
    }  
      
    void Stuedent(String name){  
        sname=name;  
    }  
      
    public String getName(){  
        return sname;  
    }  
      
    public String getSex(){  
        return ssex;  
    }  
       
     public int getAge(){  
         return sage;  
     }  
     public String toString (){  
         return sname;  
     }  
      
     //杩欎釜鏂规硶杩斿洖瀛︾敓鐨勫鍚嶏紝鎬у埆锛屽勾榫� 
     public String getStuIfo(){  
         return ("姓名:"+sname+"\n性别:"+ssex+"\n年龄:"+sage);  
     }  
 }  
