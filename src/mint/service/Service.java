package mint.service;

/**
 * An object with boolean state, commonly used to carry out work when running.
 * 
 * @author Thomas G. P. Nappo
 */
public interface Service {

	/**
	 * Starts the service. A stopped service may or may not be able to be
	 * started once stopped.
	 */
	void start();

	/**
	 * Stops the service. Once a service is stopped, it may or may not be able
	 * to be restarted.
	 * 
	 * A service cannot be stopped if not running.
	 */
	void stop();

	/**
	 * Checks if the service is running.
	 * 
	 * @return Whether or not the service is running.
	 */
	boolean isRunning();

}