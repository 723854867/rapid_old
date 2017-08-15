package org.rapid.util.common.enums;

public enum GENDER {

	/**
	 * 男
	 */
	MALE(0),
	
	/**
	 * 女
	 */
	FEMALE(1);
	
	private int mark;
	
	private GENDER(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final GENDER match(int value) {
		for (GENDER gender : GENDER.values()) {
			if (gender.mark == value)
				return gender;
		}
		return null;
	}
}
