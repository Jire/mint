package mint.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark a method as a delegate for the handling of an event dispatch.
 * 
 * <p>
 * Event handler methods should contain the {@link Event} type that they are
 * expected to handle as a single argument. That is, they should only have one
 * argument, which is the {@link Event} type that they should handle.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

	/**
	 * The authority given to the event-handler to determine the final state of
	 * the event in which is being handled.
	 * 
	 * @return The priority of the event-handler.
	 */
	EventPriority priority() default EventPriority.NORMAL;

	/**
	 * Whether or not to ignore event-cancellation.
	 * 
	 * <p>
	 * If this is <tt>true</tt>, an event-handler will execute even if an event
	 * that they are designated to handle has been cancelled.
	 * 
	 * @return Whether the event-handler should ignore the cancellation state of
	 *         an event.
	 * @see {@link CancellableEvent}
	 */
	boolean ignoreCancelled() default false;

}