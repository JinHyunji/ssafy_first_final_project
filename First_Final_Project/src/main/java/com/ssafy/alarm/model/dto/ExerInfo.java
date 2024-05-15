package com.ssafy.alarm.model.dto;

public class ExerInfo {
	private int infoId;
	private String content;
	private String reference;
	private String link;
	
	public ExerInfo() {
		// TODO Auto-generated constructor stub
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ExerInfo [infoId=" + infoId + ", content=" + content + ", reference=" + reference + ", link=" + link
				+ "]";
	}
	
	
}
