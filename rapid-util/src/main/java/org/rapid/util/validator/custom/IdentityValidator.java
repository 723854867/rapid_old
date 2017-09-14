package org.rapid.util.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rapid.util.validator.Validator;

public class IdentityValidator implements ConstraintValidator<Identity, String> {

	@Override
	public void initialize(Identity constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return null == value ? true : Validator.isIdentity(value);
	}
}
