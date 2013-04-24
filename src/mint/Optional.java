package mint;

import static mint.Preconditions.checkNotNull;

/**
 * An immutable object that may contain a non-null reference to another object.
 * Each instance of this type either contains a non-null reference, or contains
 * nothing (in which case we say that the reference is "absent"); it is never
 * said to <i>"contain <tt>null</tt>"</i>.
 * 
 * @author Thomas G. P. Nappo
 * 
 * @param <T>
 *            The type of the reference.
 */
public final class Optional<T> implements Supplier<T> {

	/**
	 * The contained instance reference of the optional.
	 * 
	 * <p>
	 * This reference is supplied for the supplier.
	 * </p>
	 */
	private final T reference;

	/**
	 * Constructs a new optional instance.
	 * 
	 * @param reference
	 *            A {@link Nullable} reference that is used for the supplier.
	 */
	private Optional(@Nullable T reference) {
		this.reference = reference;
	}

	/**
	 * Returns the contained instance so long as it is present.
	 * 
	 * @return The contained instance of this optional.
	 * 
	 * @throws NullPointerException
	 *             If the contained instance is not present.
	 */
	@Override
	public T get() {
		checkNotNull(reference);
		return reference;
	}

	/**
	 * Returns <tt>true</tt> if this holder contains an instance which is not
	 * <tt>null</tt>.
	 * 
	 * @return Whether or not this holder contains an instance which is not
	 *         <tt>null</tt>.
	 */
	public boolean isPresent() {
		return reference != null;
	}

	/**
	 * Returns the contained instance if it is present; <tt>defaultValue</tt>
	 * otherwise.
	 * 
	 * <p>
	 * If no default value should be required because the instance is known to
	 * be present, use {@link #get()} instead. For a default value of
	 * <tt>null</tt>, use {@link #orNull()}.
	 * </p>
	 * 
	 * @param defaultValue
	 *            The default value to be returned should the contained instance
	 *            not be present.
	 * @return The contained instance if it is present or <tt>defaultValue</tt>
	 *         otherwise.
	 */
	public T or(T defaultValue) {
		return isPresent() ? get() : defaultValue;
	}

	/**
	 * Returns the contained instance if it is present; <tt>null</tt> otherwise.
	 * 
	 * <p>
	 * If the instance is known to be present, use {@link #get()} instead.
	 * </p>
	 * 
	 * @return The contained instance if it is present or <tt>null</tt>
	 *         otherwise.
	 */
	public T orNull() {
		return reference;
	}

	/**
	 * Returns an <tt>Optional</tt> instance containing the given non-
	 * <tt>null</tt> reference.
	 * 
	 * @param reference
	 *            A non-<tt>null</tt> reference.
	 * @return An <tt>Optional</tt> instance containing the given non-
	 *         <tt>null</tt>.
	 * 
	 * @throws NullPointerException
	 *             If the specified reference is <tt>null</tt>.
	 */
	public static <T> Optional<T> of(T reference) {
		checkNotNull(reference);
		return new Optional<T>(reference);
	}

	/**
	 * If the specified <tt>nullableReference</tt> is non-<tt>null</tt>, returns
	 * an <tt>Optional</tt> instance containing that reference; otherwise
	 * returns {@link #absent()}.
	 * 
	 * @param nullableReference
	 *            A {@link Nullable} reference.
	 * @return An <tt>Optional</tt> instance containing the specified
	 *         <tt>nullableReference</tt> so long as the reference is non-
	 *         <tt>null</tt>; {@link #absent} otherwise.
	 */
	public static <T> Optional<T> fromNullable(@Nullable T nullableReference) {
		return new Optional<T>(nullableReference);
	}

	/**
	 * Returns an <tt>Optional</tt> instance with no contained reference.
	 * 
	 * @return An <tt>Optional</tt> instance with no contained reference.
	 */
	public static <T> Optional<T> absent() {
		return fromNullable(null);
	}

}