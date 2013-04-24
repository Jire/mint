package mint.inject;

import java.lang.annotation.Annotation;

/**
 * Collects configuration information (primarily <i>bindings</i>) which will be
 * used to create an {@link Injector}.
 * 
 * @author Thomas G. P. Nappo
 */
@ImplementedBy(UniversalBinder.class)
public interface Binder {

	/**
	 * Binds a definition type (such as an interface type) to an implementation
	 * type. An {@link Injector} will construct an instance of the
	 * implementation type where the definition type is found.
	 * 
	 * @param binding
	 *            A binding that binds a definition (a {@code Class<T>}) to an
	 *            implementation (a {@code Class<? extends T>}) in a scope {a
	 *            {@link Scope}.
	 */
	<T> void bindImplementation(ImplementationBinding<T> binding);

	/**
	 * Returns the matching implementation type for the provided definition.
	 * 
	 * @param definition
	 *            The type that defines a generic type (usually an interface or
	 *            abstract class).
	 * @return The matching implementation type for the provided definition.
	 */
	<T> ImplementationBinding<T> getBinding(Class<T> definition);

	/**
	 * Checks if the given type definition contains a bound implementation.
	 * 
	 * @param definition
	 *            The type that defines a generic type (usually an interface or
	 *            abstract class).
	 * @return Whether or not the given type definition contains a bound
	 *         implementation.
	 */
	boolean doesImplementationExist(Class<?> definition);

	/**
	 * Binds a definition type to an object annotated with a specified
	 * annotation.
	 * 
	 * @param binding
	 *            A binding that binds a type (a {@code Class<T>}) to a value (a
	 *            {@code T}) marked by some annotation (a
	 *            {@code Class<? extends Annotation>}).
	 */
	<T> void bindConstant(ConstantBinding<T> binding);

	/**
	 * Returns the matching constant value for the provided type annotated by
	 * the provided annotation.
	 * 
	 * @param type
	 *            The type that defines the generic type of the returned object.
	 * @param annotation
	 *            The binding annotation that values of the specified type
	 *            should be annotated with for association.
	 * @return The object value that the type is bound to.
	 */
	<T> T getConstant(Class<T> type, Class<? extends Annotation> annotation);

	/**
	 * Checks if the given type definition annotated with the specified
	 * annotation contains a matching value binding.
	 * 
	 * @param type
	 *            The type that defines a generic type of the object.
	 * @param annotation
	 *            The binding annotation that values of the specified type
	 *            should be annotated with for association.
	 * @return Whether or not the given type definition annotated with the
	 *         specified annotation contains a matching value binding.
	 */
	boolean doesConstantExist(Class<?> type,
			Class<? extends Annotation> annotation);

	<T> void bindSingleton(Class<?> type, Object value);

	<T> T getSingleton(Class<?> type);

	boolean doesSingletonExist(Class<?> type);

	/**
	 * Uses the given module to configure more bindings.
	 * 
	 * @param module
	 *            The module used to configure more bindings.
	 */
	void install(Module module);

}