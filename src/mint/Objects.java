package mint;

import static mint.Preconditions.checkArgument;
import static mint.Preconditions.checkNotNull;

/**
 * Static-utility methods that can operate on any {@link Object}.
 * 
 * @author Thomas G. P. Nappo
 */
public final class Objects extends NotConstructable {

	/**
	 * Returns the first of given parameters that is not <tt>null</tt> so long
	 * as one match is found.
	 * 
	 * @param items
	 *            The given items to search for the first non-<tt>null</tt>.
	 * @return The first item of the given parameters that is not <tt>null</tt>.
	 * @throws NullPointerException
	 *             If none of the given parameters are not <tt>null</tt>.
	 */
	public static <T> T firstNonNull(T... items) {
		checkNotNull(items);
		checkArgument(items.length > 0);

		for (T item : items) {
			if (item != null) {
				return item;
			}
		}
		throw new NullPointerException();
	}

	/**
	 * Determines whether two possibly-<tt>null</tt> objects are equal.
	 * 
	 * @param first
	 *            The first possibly-<tt>null</tt> object.
	 * @param second
	 *            The second possibly-<tt>null</tt> object.
	 * @return Whether or not the two possibly-<tt>null</tt> objects are equal.
	 */
	public static boolean equal(@Nullable Object first, @Nullable Object second) {
		return (first == null && second == null)
				|| (first != null && second != null && first.equals(second));
	}

	/**
	 * <tt>Objects</tt> is a static-utility class and should therefore never be
	 * constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private Objects() {
		super();
	}

}