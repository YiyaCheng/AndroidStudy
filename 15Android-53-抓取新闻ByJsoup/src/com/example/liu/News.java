package com.example.liu;

import java.io.Serializable;

public class News implements Serializable {

	private String title;//����
	private String time_count;
	private String tips;//��Ҫ����
	private String detailContent;//��ϸ����
	private String picUrl;//����ͼƬ����
	
	
	public News(String title,  String tips, String picUrl,String detailContent,String time_count) {
		super();
		this.title = title;
		this.time_count = time_count;
		this.tips = tips;
		this.detailContent = detailContent;
		this.picUrl = picUrl;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime_count() {
		return time_count;
	}
	public void setTime_count(String time_count) {
		this.time_count = time_count;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getDetailContent() {
		return detailContent;
	}
	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
