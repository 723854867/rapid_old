package org.rapid.util.validator.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 车架号
 * 
 * @author ahab
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CarVinValidator.class)
@Documented
public @interface CarVin {

	String message() default "{org.btkj.car.vin}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
