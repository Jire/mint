package mint;

/**
 * A helper mechanism used to define objects that have decorated functionality
 * which is, by default, passed to a <i>delegate</i> of type <tt>T</tt>.
 * 
 * @author Thomas G. P. Nappo
 * 
 * @param <T>
 *            The type of <i>delegate</i> to use for forwarding. An
 *            implementation forwarder typically implements this type.
 */
@Experimental
public abstract class Forwarder<T> {

	/**
	 * The delegate object which executes default method calls.
	 */
	private final T delegate;

	/**
	 * Constructs a new forwarder.
	 * 
	 * @param delegate
	 *            The delegate object which executes default method calls.
	 */
	public Forwarder(T delegate) {
		this.delegate = delegate;
	}

	/**
	 * Returns the forwarder's delegate object which executes default method
	 * calls.
	 * 
	 * @return The forwarder's {@link #delegate}.
	 */
	protected final T delegate() {
		return delegate;
	}

}