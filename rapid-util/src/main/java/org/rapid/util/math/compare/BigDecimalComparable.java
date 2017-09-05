package org.rapid.util.math.compare;

import java.math.BigDecimal;

public enum BigDecimalComparable implements Comparable<BigDecimal> {
	
	SINGLETON;

	@Override
	public boolean compare(Comparison symbol, BigDecimal src, BigDecimal... targets) {
		int len = targets.length;
		if (0 == len)
			throw new IllegalArgumentException("Comparable targets error");
		switch (symbol) {
		case eq:
			return targets[0].compareTo(src) == 0;
		case gt:
			return src.compareTo(targets[0]) > 0;
		case gte:
			return src.compareTo(targets[0]) >= 0;
		case lt:
			return src.compareTo(targets[0]) < 0;
		case lte:
			return src.compareTo(targets[0]) <= 0;
		case bteween:
		case lbteween:
		case rbteween:
			if (2 != len)
				throw new IllegalArgumentException("Comparable targets error");
			if (symbol == Comparison.bteween)
				return ((src.compareTo(targets[0])) > 0 && (src.compareTo(targets[1]) < 0));
			else if (symbol == Comparison.lbteween) 
				return ((src.compareTo(targets[0])) >= 0 && (src.compareTo(targets[1]) < 0));
			else
				return ((src.compareTo(targets[0])) > 0 && (src.compareTo(targets[1]) <= 0));
		default:
			return false;
		}
	}
}
