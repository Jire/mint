package mint.event;

/**
 * Implementations of this interface can handle events dispatched to an
 * {@link EventManager}.
 * 
 * <p>
 * An event listener expresses interest in handling some set of {@link Event}
 * types by implementing methods annotated by {@link EventHandler} and contain
 * the <tt>Event</tt> type as the method's single argument.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
public interface EventListener {
}