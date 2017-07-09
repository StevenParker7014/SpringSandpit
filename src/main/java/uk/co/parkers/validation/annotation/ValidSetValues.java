package uk.co.parkers.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSetValues {
	
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
}
