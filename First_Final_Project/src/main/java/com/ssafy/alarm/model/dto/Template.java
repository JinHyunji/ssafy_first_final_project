package com.ssafy.alarm.model.dto;

public class Template {
	private int tempId;
	private String exerType; // 이것도... 외래키로 참조가 안되는데 변수명이 동일해도 괜찮을까?
	private String videoId; // 이것도... 외래키로 참조가 안되는데 변수명이 동일해도 괜찮을까?
	private String img; // img가 alarm의 변수 img 이름이 동일해도 괜찮을까?
	private int count;
}
