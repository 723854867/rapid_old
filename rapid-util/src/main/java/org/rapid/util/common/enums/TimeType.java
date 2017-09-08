package org.rapid.util.common.enums;

/**
 * 时间类型
 * 
 * @author ahab
 */
public enum TimeType {

	YEAR(1),
	
	MONTH(2),
	
	DAY(3),
	
	WEEK(4),
	
	SEASON(5),
	
	HOUR(6),
	
	MINUTES(7),
	
	SECOND(8),
	
	MILLISECOND(9),
	
	NANOSECOND(10);
	
	private int mark;
	
	private TimeType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final TimeType match(int mark) {
		for (TimeType type : TimeType.values()) {
			if (type.mark == mark)
				return type;
		}
		return null;
	}
}
