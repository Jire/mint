package mint;

/**
 * Used to supply an object of a single type.
 * 
 * @param <T>
 *            The type of the supplied object.
 * 
 * @author Thomas G. P. Nappo
 */
public interface Supplier<T> {

	/**
	 * Retrieves the supplied instance.
	 * 
	 * <p>
	 * The return value may or may not be a new instance, depending on
	 * implementation.
	 * </p>
	 * 
	 * @return An instance of the appropriate type.
	 */
	public T get();

}