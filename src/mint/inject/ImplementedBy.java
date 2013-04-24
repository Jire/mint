package mint.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A pointer to the default implementation of a type.
 * 
 * @author Thomas G. P. Nappo
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementedBy {

	/**
	 * The concrete implementation type.
	 */
	Class<?> value();

}