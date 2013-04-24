package mint.inject;

/**
 * A binding that binds a definition (a {@code Class<T>}) to an implementation
 * (a {@code Class<? extends T>}) in a scope {a {@link Scope}.
 * 
 * @author Thomas G. P. Nappo
 * 
 * @param <T>
 *            The binding's component's type.
 */
public final class ImplementationBinding<T> {

	/**
	 * The type that defines a generic type (usually an interface or abstract
	 * class).
	 */
	private final Class<T> definition;

	/**
	 * The concrete implementation type to bind the definition to.
	 */
	private final Class<? extends T> implementation;

	/**
	 * The visibility scope of the binding.
	 */
	private Scope scope;

	/**
	 * Constructs a new implementation binding.
	 * 
	 * @param definition
	 *            The type that defines a generic type (usually an interface or
	 *            abstract class).
	 * @param implementation
	 *            The concrete implementation type to bind the definition to.
	 * @param scope
	 *            The visibility scope of the binding.
	 */
	public ImplementationBinding(Class<T> definition,
			Class<? extends T> implementation, Scope scope) {
		this.definition = definition;
		this.implementation = implementation;
		this.scope = scope;
	}

	/**
	 * Constructs a new implementation binding with a {@link Scope#DEFAULT}
	 * scope.
	 * 
	 * @param definition
	 *            The type that defines a generic type (usually an interface or
	 *            abstract class).
	 * @param implementation
	 *            The concrete implementation type to bind the definition to.
	 */
	public ImplementationBinding(Class<T> definition,
			Class<? extends T> implementation) {
		this(definition, implementation, Scope.DEFAULT);
	}

	/**
	 * Retrieves the binding's definition.
	 * 
	 * @return The type that defines a generic type (usually an interface or
	 *         abstract class).
	 */
	public Class<T> getDefinition() {
		return definition;
	}

	/**
	 * Retrieves the binding's implementation.
	 * 
	 * @return The concrete implementation type to bind the definition to.
	 */
	public Class<? extends T> getImplementation() {
		return implementation;
	}

	/**
	 * Retrieves the binding's scope.
	 * 
	 * @return The visibility scope of the binding.
	 */
	public Scope getScope() {
		return scope;
	}

	/**
	 * Sets the scope of the binding.
	 * 
	 * @param scope
	 *            The visibility scope of the binding.
	 */
	protected void setScope(Scope scope) {
		this.scope = scope;
	}

}