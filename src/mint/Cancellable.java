package mint;

/**
 * Something, usually an <tt>occurrence</tt>, that can be cancelled.
 * 
 * <p>
 * Cancellation is performed by setting the cancellation state of the object.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
public interface Cancellable {

	/**
	 * Retrieves the cancellation state, which typically determines whether or
	 * not the partitioned task should be executed.
	 * 
	 * @return Whether or not the task partitioned to execute should be
	 *         cancelled.
	 */
	boolean isCancelled();

	/**
	 * Sets the cancellation state of the task.
	 * 
	 * <p>
	 * As the cancellation state is determined before executing a task, setting
	 * the state of cancellation will not guarantee the cancellation or
	 * proceeding of a task.
	 * </p>
	 * 
	 * @param cancelled
	 *            Whether or not the task partitions to execute should be
	 *            cancelled.
	 */
	void setCancelled(boolean cancelled);

}