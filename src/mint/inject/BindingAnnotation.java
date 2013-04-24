package mint.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark annotations which are used for binding. You must annotate binder
 * annotations with {@code @Retention(RetentionPolicy.RUNTIME)}.
 * 
 * @author Thomas G. P. Nappo
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.ANNOTATION_TYPE)
public @interface BindingAnnotation {
}