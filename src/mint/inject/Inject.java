package mint.inject;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotates members of your implementation class (constructors, methods and
 * fields) into which the {@link Injector} should inject values. The injector
 * fulfills injection requests for every instance it constructs. The class being
 * constructed must have exactly one of its constructors marked with
 * {@code @Inject} or must have a constructor taking no parameters. The injector
 * then proceeds to perform method and field injections.
 * 
 * <p>
 * A member can be injected regardless of its <i>Java</i> access modifier
 * (private, default, protected, public).
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
@Documented
@Retention(RUNTIME)
@Target({ METHOD, CONSTRUCTOR, FIELD })
public @interface Inject {

	/**
	 * If <tt>true</tt> and the appropriate binding is not found, an
	 * {@link Injector} will skip injection of this method or field rather than
	 * produce an error. When applied to a field, any default value already
	 * assigned to the field will remain (the injector will <b>not</b> actively
	 * <tt>null</tt> out the field). When applied to a method, the method will
	 * only be invoked if bindings for <i>all</i> parameters are found. When
	 * applied to a constructor, an error will result upon the creation of the
	 * injector.
	 */
	boolean optional() default false;

}