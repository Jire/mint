package mint.service;

/**
 * An abstract implementation of {@link Service} which provides separate
 * {@link #onStart()} and {@link #onStop()} methods to perform logical startup
 * and shutdown operations yielded in a non-abstract implementation.
 * 
 * <p>
 * This implementation manages the running state. Services using this
 * implementation will be unable to be restarted.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
public abstract class AbstractService implements Service {

	/**
	 * The running state of the service.
	 * 
	 * <p>
	 * This boolean state is used to denote whether the service is active.
	 * </p>
	 */
	private boolean running;

	@Override
	public final void start() {
		if (isRunning()) {
			throw new IllegalStateException("The service is already running!");
		}
		onStart();
		setRunning(true);
	}

	/**
	 * This method is called by {@link #start()} to initiate service startup.
	 * 
	 * <p>
	 * Implementations should perform startup operations on call.
	 * </p>
	 */
	protected abstract void onStart();

	@Override
	public final void stop() {
		if (!isRunning()) {
			throw new IllegalStateException(
					"The service has already been stopped!");
		}
		onStop();
		setRunning(false);
	}

	/**
	 * This method is called by {@link #stop()} to initiate service shutdown.
	 * 
	 * <p>
	 * Implementations should perform shutdown operations on call.
	 * </p>
	 */
	protected abstract void onStop();

	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the running state of the service.
	 * 
	 * @param running
	 *            Whether or not the service is running.
	 */
	private void setRunning(boolean running) {
		this.running = running;
	}

}