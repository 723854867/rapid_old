package org.rapid.util.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.validator.Validator;

public class CarEngineValidator implements ConstraintValidator<CarEngine, String> {

	@Override
	public void initialize(CarEngine constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : Validator.isVehicleEngine(value);
	}
}
