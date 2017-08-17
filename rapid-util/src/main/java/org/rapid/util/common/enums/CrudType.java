package org.rapid.util.common.enums;

public enum CrudType {

	CREATE(1),
	
	RETRIEVE(2),
	
	UPDATE(4),
	
	DELETE(8);
	
	private int mark;
	
	private CrudType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final CrudType match(int type) { 
		for (CrudType temp : CrudType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
