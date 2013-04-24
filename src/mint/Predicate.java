package mint;

/**
 * A functional interface which is used to state, affirm, or assert (something)
 * about a subject.
 * 
 * <p>
 * Implementations which modify state are discouraged.
 * </p>
 * 
 * @param <T>
 *            The type of input that the predicate evaluates.
 * 
 * @author Thomas G. P. Nappo
 */
public interface Predicate<T> {

	/**
	 * Checks whether the input value lies between the specified filtering
	 * criterion.
	 * 
	 * @param input
	 *            The input that the predicate should act on.
	 * @return Whether or not the input value meets the filtering criterion.
	 */
	public boolean evaluate(@Nullable T input);

}