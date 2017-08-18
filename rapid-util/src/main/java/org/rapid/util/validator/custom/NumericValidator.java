package org.rapid.util.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.validator.Validator;

public class NumericValidator implements ConstraintValidator<Numeric, String>  {

	@Override
	public void initialize(Numeric constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : Validator.isNumber(value);
	}
}
