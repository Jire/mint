package mint.event;

import mint.Cancellable;

/**
 * An {@link Event} which can be cancelled.
 * 
 * @author Thomas G. P. Nappo
 * @see {@link Cancellable}
 */
public abstract class CancellableEvent implements Event, Cancellable {

	/**
	 * Whether or not the event should be cancelled.
	 */
	private boolean cancelled;

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}