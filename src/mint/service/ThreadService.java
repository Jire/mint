package mint.service;

import mint.Experimental;
import mint.Nullable;

/**
 * An abstract implementation of {@link Service} which allocates a single thread
 * to represent the underlying implementation. Because this implementation is a
 * {@link Runnable}, implementations are required to implement
 * {@link Runnable#run}.
 * 
 * <p>
 * Consider using {@link AbstractService} if you would like to manage any
 * threading manually.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
@Experimental
public abstract class ThreadService extends AbstractService implements Runnable {

	/**
	 * The representative thread of the service.
	 * 
	 * <p>
	 * This thread uses this service's runnable implementation as its
	 * executional logic.
	 * </p>
	 */
	private final Thread thread;

	/**
	 * Constructs a new thread service with a specified thread name.
	 * 
	 * @param threadName
	 *            The underlying thread's name.
	 */
	public ThreadService(@Nullable String threadName) {
		this.thread = new Thread(this, threadName);
	}

	/**
	 * Construct a new thread service with no thread name.
	 */
	public ThreadService() {
		this(null);
	}

	/**
	 * Retrieves the representative thread of the service.
	 * 
	 * @return The service's representative thread.
	 */
	protected final Thread getThread() {
		return thread;
	}

	@Override
	protected final void onStart() {
		getThread().start();
	}

	@Override
	protected final void onStop() {
	}

}