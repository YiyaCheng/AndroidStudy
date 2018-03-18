package jxnu.edu.cn.x3321;


public class Player {
	String name;
	int image;
	String count;

	public Player(String name,int image,String count){
		this.name = name;
		this.image = image;
		this.count = count;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}

