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
	
	public static final Protocol match(String protocol) { 
		for (Protocol temp : Protocol.values()) {
			if (temp.name().equalsIgnoreCase(protocol))
				return temp;
		}
		return null;
	}
}
