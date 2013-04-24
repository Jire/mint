package mint.collect;

import static mint.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import mint.NotConstructable;
import mint.Predicate;

/**
 * Static utility methods that operate on or return objects of type
 * {@link Iterable}.
 * 
 * @author Thomas G. P. Nappo
 */
public final class Iterables extends NotConstructable {

	/**
	 * Returns the elements of <tt>unfiltered</tt> that satisfy a predicate.
	 * 
	 * @param unfiltered
	 *            An iterable containing objects of any type that will be
	 *            filtered and used as the result.
	 * @param predicate
	 *            The predicate to use for evaluation.
	 * @return An iterable containing all objects which passed the predicate's
	 *         evaluation.
	 * @throws NullPointerException
	 *             If <tt>unfiltered</tt> or <tt>predicate</tt> are
	 *             <tt>null</tt>.
	 */
	public static <T> Iterable<T> filter(Iterable<T> unfiltered,
			Predicate<T> predicate) {
		checkNotNull(unfiltered);
		checkNotNull(predicate);

		List<T> result = new ArrayList<T>();
		Iterator<T> iterator = unfiltered.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			if (predicate.evaluate(next)) {
				result.add(next);
			}
		}
		return result;
	}

	/**
	 * Returns the elements of <tt>unfiltered</tt> that satisfy a predicate.
	 * 
	 * @param unfiltered
	 *            An array containing objects of any type that will be filtered
	 *            and used as the result.
	 * @param predicate
	 *            The predicate to use for evaluation.
	 * @return An iterable containing all objects which passed the predicate's
	 *         evaluation.
	 * @throws NullPointerException
	 *             If <tt>unfiltered</tt> or <tt>predicate</tt> are
	 *             <tt>null</tt>.
	 */
	public static <T> Iterable<T> filter(T[] unfiltered, Predicate<T> predicate) {
		return filter(Arrays.asList(unfiltered), predicate);
	}

	/**
	 * Determines if the given iterable contains no elements.
	 * 
	 * @param iterable
	 *            The iterable to used for determination.
	 * @return Whether or not the given iterable contains no elements.
	 */
	public static boolean isEmpty(Iterable<?> iterable) {
		return !iterable.iterator().hasNext();
	}

	/**
	 * <tt>Iterables</tt> is a static-utility class and should therefore never
	 * be constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private Iterables() {
		super();
	}

}