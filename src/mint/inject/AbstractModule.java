package mint.inject;

import static mint.Preconditions.checkNotNull;
import static mint.Preconditions.checkState;

/**
 * A support class for {@link Module}s which reduces repetition and results in a
 * more readable configuration. Simply extend this class, implement
 * {@link #configure()}, and call the inherited methods which mirror those found
 * in {@link Binder}.
 * 
 * @author Thomas G. P. Nappo
 */
public abstract class AbstractModule implements Module {

	/**
	 * The binder to use for configuration.
	 */
	private Binder binder;

	/**
	 * Configures a {@link Binder} via the exposed methods.
	 */
	protected abstract void configure();

	@Override
	public final synchronized void configure(Binder binder) {
		checkState(this.binder == null, "Re-entry is not allowed!");
		this.binder = checkNotNull(binder);

		try {
			configure();
		} finally {
			this.binder = null;
		}
	}

	/**
	 * Gets direct access to the underlying {@code Binder}.
	 */
	protected final Binder getBinder() {
		checkState(binder != null,
				"The binder can only be used within configure()");
		return binder;
	}

	/**
	 * Returns a created linked binding builder using the specified definition.
	 * 
	 * @param definition
	 *            The type that defines a generic type (usually an interface or
	 *            abstract class).
	 * @return A created linked binding builder using the specified definition.
	 */
	protected final <T> LinkedBindingBuilder<T> bind(Class<T> definition) {
		return new LinkedBindingBuilder<T>(getBinder(), definition);
	}

}