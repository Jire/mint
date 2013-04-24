package mint.event;

import static mint.collect.Iterables.filter;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * An abstract implementation of {@link EventManager} which provides an
 * {@link EventRegistry} for use.
 * 
 * @author Thomas G. P. Nappo
 */
public abstract class AbstractEventManager implements EventManager {

	/**
	 * The registry of the event manager, used to map event listeners to the
	 * event handlers that they contain.
	 */
	private final EventRegistry registry;

	/**
	 * Constructs an event manager, requiring an already-existing registry.
	 * 
	 * @param registry
	 *            The registry of the event manager, used to map event listeners
	 *            to the event handlers that they contain.
	 */
	public AbstractEventManager(EventRegistry registry) {
		this.registry = registry;
	}

	/**
	 * Constructs an event manager, suppling a new {@link HashMap} as the
	 * registry.
	 */
	public AbstractEventManager() {
		this(new EventRegistry());
	}

	/**
	 * Retrieves the <tt>registry</tt> of the event manager, used to map event
	 * listeners to the event handlers that they contain.
	 * 
	 * @return The event manager's {@link #registry}.
	 */
	protected final EventRegistry getRegistry() {
		return registry;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final void registerListener(EventListener listener) {
		for (Method method : filter(listener.getClass().getMethods(),
				EventHandlerPredicate.instance())) {
			EventHandler eventHandler = method
					.getAnnotation(EventHandler.class);
			/*
			 * Unchecked cast suppressed because it is better to throw an
			 * exception, as it clearly signals something is wrong with the
			 * specified event handler.
			 */
			getRegistry().register(
					(Class<? extends Event>) method.getParameterTypes()[0],
					eventHandler.priority(), eventHandler.ignoreCancelled(),
					method, listener);
		}
	}

}