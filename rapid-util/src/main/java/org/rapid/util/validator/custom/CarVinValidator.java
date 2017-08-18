package org.rapid.util.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.validator.Validator;

public class CarVinValidator implements ConstraintValidator<CarVin, String> {

	@Override
	public void initialize(CarVin constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : Validator.isVehicleVin(value);
	}
}
