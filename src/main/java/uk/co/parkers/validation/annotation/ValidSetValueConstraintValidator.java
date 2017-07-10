package uk.co.parkers.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import uk.co.parkers.validation.annotation.impl.ValidSetValuesConstraint;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidSetValuesConstraint.class)
@Documented
public @interface ValidSetValueConstraintValidator {
	
	/**
	 * The key for the valid set values.
	 * @return
	 */
	String key();

	/**
	 * Error message to report if not valid
	 * @return
	 */
	String message() default "Value must be in the following list {0}";
	
	/**
	 * Localised message key.
	 * @return
	 */
	String messageKey() default "";
	
	/**
	 * Should comparisons ignore case.
	 * @return
	 */
	boolean ignoreCase() default true;
	
	/**
	 * Should validation be skipped if no valid set values found.
	 * @return
	 */
	boolean ignoreEmptyValidSetValues() default true;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
