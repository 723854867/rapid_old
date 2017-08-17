package org.rapid.util.common.enums;

public enum Protocol {

	HTTP("http"),
	
	HTTPS("https");
	
	private String value;
	
	private Protocol(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
