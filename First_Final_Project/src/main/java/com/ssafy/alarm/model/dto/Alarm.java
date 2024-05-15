package com.ssafy.alarm.model.dto;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;

public class Alarm {
	private int alarmId;
	private String title;
	private String startTime; // time을 String으로??
	private String endTime; // time을 String으로??
	private int term;
	private String cycle;
	private String exerType;
	private String videoId;
	private String img;
	private String userId;
	
	public Alarm() {
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Alarm [alarmId=" + alarmId + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", term=" + term + ", cycle=" + cycle + ", exerType=" + exerType + ", videoId=" + videoId + ", img="
				+ img + ", userId=" + userId + "]";
	}
	
}
