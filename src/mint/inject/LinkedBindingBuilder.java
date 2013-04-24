package mint.inject;

import java.lang.annotation.Annotation;

import mint.Experimental;

@Experimental
public final class LinkedBindingBuilder<T> {

	private final Binder binder;
	private final Class<T> definition;

	LinkedBindingBuilder(Binder binder, Class<T> definition) {
		this.binder = binder;
		this.definition = definition;
	}

	/**
	 * Binds the previously specified definition type to the specified
	 * implementation.
	 * 
	 * @param implementation
	 *            The concrete implementation type to bind the definition to.
	 * @return A scoped binding builder to control the scope of the binding.
	 */
	public ScopedBindingBuilder<T> to(Class<? extends T> implementation) {
		ImplementationBinding<T> binding = new ImplementationBinding<T>(
				definition, implementation);
		binder.bindImplementation(binding);
		return new ScopedBindingBuilder<T>(binder, binding);
	}

	/**
	 * Creates a new constant binding builder using the previously specified
	 * definition type and the specified annotation type to allow you to state a
	 * specified constant value.
	 * 
	 * @param annotation
	 *            The binding annotation that values of the specified type
	 *            should be annotated with for association.
	 * @return A constant binding builder to specify the constant value of the
	 *         binding.
	 */
	public ConstantBindingBuilder<T> annotatedWith(
			Class<? extends Annotation> annotation) {
		return new ConstantBindingBuilder<T>(binder, definition, annotation);
	}

}