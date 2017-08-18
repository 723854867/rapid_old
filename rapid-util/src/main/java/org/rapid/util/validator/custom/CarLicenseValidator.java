package org.rapid.util.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.validator.Validator;

public class CarLicenseValidator implements ConstraintValidator<CarLicense, String> {

	@Override
	public void initialize(CarLicense constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : Validator.isVehicleLisense(value);
	}
}
