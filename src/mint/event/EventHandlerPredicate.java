package mint.event;

import java.lang.reflect.Method;

import mint.Predicate;
import mint.inject.Singleton;

/**
 * This predicate evaluates {@link Method}s to be in accord with the
 * {@link EventHandler} specification: The method must be annotated with
 * {@code @EventHandler} and have a single {@link Event} parameter.
 * 
 * @author Thomas G. P. Nappo
 */
@Singleton
public final class EventHandlerPredicate implements Predicate<Method> {

	/**
	 * <tt>EventHandlerPredicate</tt> is a singleton and should therefore only
	 * be constructed once.
	 */
	private EventHandlerPredicate() {
	}

	@Override
	public boolean evaluate(Method input) {
		return input.isAnnotationPresent(EventHandler.class)
				&& input.getParameterTypes().length == 1
				&& Event.class.isAssignableFrom(input.getParameterTypes()[0]);
	}

	/**
	 * The once-constructed <tt>EventHandlerPredicate</tt> singleton instance.
	 */
	private static final EventHandlerPredicate INSTANCE = new EventHandlerPredicate();

	/**
	 * Returns the <tt>EventHandlerPredicate</tt> singleton instance.
	 * 
	 * @return The <tt>EventHandlerPredicate</tt> singleton instance.
	 */
	public static EventHandlerPredicate instance() {
		return INSTANCE;
	}

}