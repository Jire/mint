package mint.event;

/**
 * Represents an event handler's authority to the final state of the event in
 * which is being handled.
 * 
 * @author Thomas G. P. Nappo
 */
public enum EventPriority {

	/*
	 * The underlying implementation of an event manager will determine how
	 * these priorities should be used.
	 * 
	 * Typically, the event's handling will be dispatched by low-ordinal first,
	 * continuing to the priority of highest-ordinal for the last execution.
	 */

	/**
	 * Event call is critical and must have the first say in the event's state.
	 */
	HIGHEST,

	/**
	 * Event call is of high importance.
	 */
	HIGH,

	/**
	 * Event call is neither important nor unimportant, and may be ran normally.
	 */
	NORMAL,

	/**
	 * Event call is of low importance.
	 */
	LOW,

	/**
	 * Event call is of very low importance and should be ran last, to allow
	 * other listeners to further customize the final state of the event.
	 */
	LOWEST,

	/**
	 * Event is listened to solely for monitoring the outcome of an event.
	 * 
	 * <p>
	 * No modifications to event state should be made under this priority.
	 * </p>
	 */
	MONITOR;

}