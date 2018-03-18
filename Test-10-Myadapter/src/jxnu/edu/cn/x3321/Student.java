package jxnu.edu.cn.x3321;

public class Student {
	private String name;
	private String id;
	private int image;
	
	 public Student(int image,String name,String id){
	        this.name=name;
	        this.id=id;
	        this.image= image;
	    }
	 
	public int getImage() {  
        return image;  
    }   
	public String getName()
	{
		return name;
	}
	public String getId()
	{
		return id;
	}
	public String showMessage()
	{
		return ("ĞÕÃû£º"+name+"\nÑ§ºÅ£º"+id);
	}
	
}
