package org.rapid.util.validator.custom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

	private String format;
	
	@Override
	public void initialize(Date date) {
		this.format = date.fomat();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (null == value)
			return true;
		
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getDefault());
		try {
			df.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
