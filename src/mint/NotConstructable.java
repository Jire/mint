package mint;

import static java.lang.String.format;

/**
 * Extend this class when you creating a class that cannot be constructed (e.g.
 * a static utility class).
 * 
 * <p>
 * To ensure no construction on compilation provide a <tt>private</tt> no-args
 * constructor. It is good practice to document such a constructor with the
 * reason why the class should not be constructed.
 * </p>
 * 
 * @author Thomas G. P. Nappo
 */
public abstract class NotConstructable {

	/**
	 * Immediately throws an <tt>UnsupportedOperationException</tt>.
	 * 
	 * @throws UnsupportedOperationException
	 */
	protected NotConstructable() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Immediately throws an <tt>UnsupportedOperationException</tt> with the
	 * given message.
	 * 
	 * @param message
	 *            The exception message to use for the
	 *            <tt>UnsupportedOperationException</tt>.
	 * @param messageArgs
	 *            Arguments referenced by the format specifiers in the format
	 *            string. If there are more arguments than format specifiers,
	 *            the extra arguments are ignored. The number of arguments is
	 *            variable and may be zero. The maximum number of arguments is
	 *            limited by the maximum dimension of a Java array as defined by
	 *            <cite>The Java&trade; Virtual Machine Specification</cite>.
	 * @throws UnsupportedOperationException
	 */
	protected NotConstructable(String message, Object... messageArgs) {
		throw new UnsupportedOperationException(format(message, messageArgs));
	}

}