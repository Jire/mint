package mint.event;

import static mint.Preconditions.checkNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import mint.Nullable;
import mint.inject.Singleton;

/**
 * An event manager which implements all universal features.
 * 
 * @author Thomas G. P. Nappo
 */
@Singleton
public final class UniversalEventManager extends AbstractEventManager {

	@Override
	public void dispatchEvent(Event event) {
		checkNotNull(event);

		CancellableEvent cancellableEvent = null;
		boolean cancellable;
		if (cancellable = event instanceof CancellableEvent) {
			cancellableEvent = (CancellableEvent) event;
		}

		fireEvents(false, event, cancellableEvent, cancellable);
		fireEvents(true, event, cancellableEvent, cancellable);
	}

	/**
	 * Fires all events of the specified type using the specified
	 * configurations.
	 * 
	 * @param considerCancellation
	 *            Whether or not to consider cancellation.
	 * @param event
	 *            The event which specifies the type of events to fire.
	 * @param cancellableEvent
	 *            The cancellable event instance.
	 * @param cancellable
	 *            Whether or not the event is cancellable.
	 */
	private void fireEvents(boolean considerCancellation, Event event,
			@Nullable CancellableEvent cancellableEvent, boolean cancellable) {
		// Event handlers that consider cancellation will run
		for (EventPriority priority : EventPriority.values()) {
			Map<Method, EventListener> internalMapping = getRegistry()
					.getMethodMap(event.getClass(), priority,
							!considerCancellation);
			if (internalMapping != null) {
				for (Entry<Method, EventListener> entry : internalMapping
						.entrySet()) {
					try {
						entry.getKey().invoke(entry.getValue(), event);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						/*
						 * Delegate any exceptions that occur from the method to
						 * a runtime exception.
						 */
						throw new RuntimeException(e);
					}
					// Immediately return in the case of the event being
					// cancelled.
					if (considerCancellation && cancellable
							&& cancellableEvent.isCancelled()) {
						return;
					}
				}
			}
		}
	}

}