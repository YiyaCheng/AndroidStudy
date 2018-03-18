package jxnu.edu.cn.x3321;

public class Country {
	int images;
	String names;
	public Country(int images,String names)
	{
		this.images=images;
		this.names=names;
	}
	
	public void setImage(int images)
	{
		this.images=images;
	}
    public int getImage()
    {
    	return images;
    }
    public void setName(String names)
    {
    	this.names=names;
    }
    public  String getName()
    {
    	return names;
    }
}
