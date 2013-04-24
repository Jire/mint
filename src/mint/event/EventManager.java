package mint.event;

import mint.inject.ImplementedBy;

/**
 * An event manager provides support for dynamic {@link Event} dispatching along
 * with {@link EventListener} registration.
 * 
 * @author Thomas G. P. Nappo
 */
@ImplementedBy(UniversalEventManager.class)
public interface EventManager {

	/**
	 * Dispatches an event to be handled by all registered {@link EventListener}s.
	 * 
	 * @param event
	 *            The event type to dispatch.
	 */
	public void dispatchEvent(Event event);

	/**
	 * Registers an event listener as a target for all further event dispatches.
	 * 
	 * @param listener
	 *            The event listener to register.
	 */
	public void registerListener(EventListener listener);

}