package mint.inject;

/**
 * Builds the graphs of objects that make up your application. The injector
 * tracks the dependencies for each type and uses bindings to inject them.
 * 
 * @author Thomas G. P. Nappo
 */
@ImplementedBy(UniversalInjector.class)
public interface Injector {

	/**
	 * Returns the appropriate instance for the given injection type definition.
	 * 
	 * @param definition
	 *            The type that defines the instance to return.
	 * @return An appropriate instance for the given injection type definition.
	 */
	<T> T getInstance(Class<T> definition);

	/**
	 * Returns this injector's binder. This method should not be commonly used
	 * by application developers.
	 * 
	 * @return This injector's binder.
	 */
	Binder getBinder();

}