package mint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Signifies that <tt>null</tt> may be a value of the annotated element.
 * 
 * <p>
 * <b>For methods</b> this means that <tt>null</tt> is a possible return type. <br>
 * <b>For parameters</b> this means that <tt>null</tt> may be specified as a
 * value.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
@Documented
@Target({ ElementType.METHOD, ElementType.PARAMETER })
public @interface Nullable {
}