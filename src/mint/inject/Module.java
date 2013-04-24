package mint.inject;

/**
 * Contributes configuration information, typically interface bindings, which
 * will be used to create an {@link Injector}.
 * 
 * @author Thomas G. P. Nappo
 */
public interface Module {

	/**
	 * Contributes bindings and other configurations for this module to
	 * {@code binder}.
	 * 
	 * <p>
	 * <strong>Do not invoke this method directly</strong> to install
	 * submodules. Instead use {@link Binder#install(Module)}, which ensures
	 * that {@link Provides provider methods} are discovered.
	 */
	void configure(Binder binder);

}