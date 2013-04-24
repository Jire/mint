package mint.inject;

import java.lang.annotation.Annotation;

/**
 * A binding that binds a type (a {@code Class<T>}) to a value (a {@code T})
 * marked by some annotation (a {@code Class<? extends Annotation>}).
 * 
 * @author Thomas G. P. Nappo
 * 
 * @param <T>
 *            The binding's component's type.
 */
public final class ConstantBinding<T> {

	/**
	 * The type that defines a generic type of the object.
	 */
	private final Class<T> type;

	/**
	 * The binding annotation that values of the specified type should be
	 * annotated with for association.
	 */
	private final Class<? extends Annotation> annotation;

	/**
	 * The object value that the type will be bound to.
	 */
	private final T value;

	/**
	 * Constructs a new constant binding.
	 * 
	 * @param type
	 *            The type that defines a generic type of the object.
	 * @param annotation
	 *            The binding annotation that values of the specified type
	 *            should be annotated with for association.
	 * @param value
	 *            The object value that the type will be bound to.
	 */
	public ConstantBinding(Class<T> type,
			Class<? extends Annotation> annotation, T value) {
		this.type = type;
		this.annotation = annotation;
		this.value = value;
	}

	/**
	 * Retrieves the binding's type.
	 * 
	 * @return The type that defines a generic type of the object.
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * Retrieves the binding's annotation.
	 * 
	 * @return The binding annotation that values of the specified type should
	 *         be annotated with for association.
	 */
	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	/**
	 * Retrieves the binding's value.
	 * 
	 * @return The object value that the type will be bound to.
	 */
	public T getValue() {
		return value;
	}

}