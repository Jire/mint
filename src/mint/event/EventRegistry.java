package mint.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * An event registry can both store and retrieve event mappings.
 * 
 * @author Thomas G. P. Nappo
 */
public final class EventRegistry {

	/**
	 * The {@link Map} backing of the registry. This is used to store and
	 * retrieve registered event mappings.
	 */
	private final Map<Integer, Map<Method, EventListener>> backing = new HashMap<Integer, Map<Method, EventListener>>();

	/**
	 * Registers an event mapping.
	 * 
	 * @param event
	 *            The event type.
	 * @param priority
	 *            The priority of the event mapping.
	 * @param ignoreCancellation
	 *            Whether or not to ignore cancellation.
	 * @param method
	 *            The method to invoke upon event execution.
	 * @param listener
	 *            The listener that contains the specified method.
	 */
	public synchronized void register(Class<? extends Event> event,
			EventPriority priority, boolean ignoreCancellation, Method method,
			EventListener listener) {
		final int key = createKey(event, priority, ignoreCancellation);

		Map<Method, EventListener> methodMap = backing.get(key);
		if (methodMap == null) {
			backing.put(key, methodMap = new HashMap<Method, EventListener>());
		}
		methodMap.put(method, listener);

		backing.put(key, methodMap);
	}

	/**
	 * Returns a method mapping using the given configurations or <tt>null</tt>
	 * if no mapping exists.
	 * 
	 * @param event
	 *            The event type of the event mapping.
	 * @param priority
	 *            The priority of the event mapping.
	 * @param ignoreCancellation
	 *            Whether or not to ignore cancellation.
	 * @return A method mapping using the given configurations or <tt>null</tt>
	 *         if no mapping exists.
	 */
	public synchronized Map<Method, EventListener> getMethodMap(
			Class<? extends Event> event, EventPriority priority,
			boolean ignoreCancellation) {
		return backing.get(createKey(event, priority, ignoreCancellation));
	}

	/**
	 * Creates a formatted key name with the given configurations.
	 * 
	 * @param event
	 *            The event type.
	 * @param priority
	 *            The priority of the event handler.
	 * @param ignoreCancellation
	 *            Whether or not to ignore cancellation.
	 * @return A formatted key name with the given configurations.
	 */
	private static int createKey(Class<? extends Event> event,
			EventPriority priority, boolean ignoreCancellation) {
		return (event.getName() + "-" + priority.name() + ":" + ignoreCancellation)
				.hashCode();
	}

}