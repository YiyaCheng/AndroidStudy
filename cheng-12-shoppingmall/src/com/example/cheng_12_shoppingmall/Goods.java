package com.example.cheng_12_shoppingmall;

public class Goods {
	public int images;
	public String textTitle;
	public String textContent;
	public String prices;
	public Goods(int images,String textTitle,String textContent,String prices)
	{
		this.images=images;
		this.textTitle=textTitle;
		this.prices=prices;
		this.textContent=textContent;
	}
	/*public int getImage(int images)
	{
		return images;
	}
   public String getPrice(String prices)
   {
	   return prices;
   }
   public String getTextTitle(String textTitle)
   {
	   return textTitle;
   }
   public String getTextContent(String textContent)
   {
	   return textContent;
   }*/
}
