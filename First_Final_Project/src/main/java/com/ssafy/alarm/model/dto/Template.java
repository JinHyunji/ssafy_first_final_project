package com.ssafy.alarm.model.dto;

public class Template {
	private int tempId;
	private String exerType; // 이것도... 외래키로 참조가 안되는데 변수명이 동일해도 괜찮을까?
	private String videoId; // 이것도... 외래키로 참조가 안되는데 변수명이 동일해도 괜찮을까?
	private String img; // img가 alarm의 변수 img 이름이 동일해도 괜찮을까?
	private int count;
	
	public Template() {
	}

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	public String getExerType() {
		return exerType;
	}

	public void setExerType(String exerType) {
		this.exerType = exerType;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Template [tempId=" + tempId + ", exerType=" + exerType + ", videoId=" + videoId + ", img=" + img
				+ ", count=" + count + "]";
	}
	
}
