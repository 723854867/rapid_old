package org.rapid.util.validator.custom;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StreamValidator implements ConstraintValidator<Stream, InputStream> {
	
	private int maxmium;

	@Override
	public void initialize(Stream constraintAnnotation) {
		this.maxmium = constraintAnnotation.maxmium();
	}

	@Override
	public boolean isValid(InputStream value, ConstraintValidatorContext context) {
		if (null == value)
			return true;
		try {
			int total = value.available();
			return total <= maxmium;
		} catch (IOException e) {
			return false;
		}
	}
}
