package mint;

import static java.lang.String.format;

/**
 * Simple static utility methods to verify proper state of method arguments.
 * 
 * @author Thomas G. P. Nappo
 */
public final class Preconditions extends NotConstructable {

	/**
	 * Ensures that an object reference passed as a parameter to the calling
	 * method is not <tt>null</tt>.
	 * 
	 * @param reference
	 *            The object reference to perform validation.
	 * @return The non-null reference that was validated.
	 * @throws NullPointerException
	 *             If the reference is <tt>null</tt>.
	 */
	public static <T> T checkNotNull(T reference) {
		return checkNotNull(reference, null);
	}

	/**
	 * Ensures that an object reference passed as a parameter to the calling
	 * method is not <tt>null</tt>.
	 * 
	 * @param reference
	 *            The object reference to perform validation.
	 * @param errorArguments
	 *            Arguments referenced by the format specifiers in the format
	 *            string. If there are more arguments than format specifiers,
	 *            the extra arguments are ignored. The number of arguments is
	 *            variable and may be zero. The maximum number of arguments is
	 *            limited by the maximum dimension of a Java array as defined by
	 *            <cite>The Java&trade; Virtual Machine Specification</cite>.
	 * @return The non-null reference that was validated.
	 * @throws NullPointerException
	 *             If the reference is <tt>null</tt>.
	 */
	public static <T> T checkNotNull(T reference, String errorMessage,
			Object... errorArguments) {
		if (reference == null) {
			if (errorMessage != null) {
				throw new NullPointerException(format(errorMessage,
						errorArguments));
			}
			throw new NullPointerException();
		}
		return reference;
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to
	 * the calling method.
	 * 
	 * @param expression
	 *            The representative expression, as a <tt>boolean</tt>.
	 * @throws IllegalArgumentException
	 *             If the expression is <tt>false</tt>.
	 */
	public static void checkArgument(boolean expression) {
		checkArgument(expression, null);
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to
	 * the calling method.
	 * 
	 * @param expression
	 *            The representative expression, as a <tt>boolean</tt>.
	 * @param errorMessage
	 *            The <i>format string</i> message to use if the check fails.
	 * @param errorArguments
	 *            Arguments referenced by the format specifiers in the format
	 *            string. If there are more arguments than format specifiers,
	 *            the extra arguments are ignored. The number of arguments is
	 *            variable and may be zero. The maximum number of arguments is
	 *            limited by the maximum dimension of a Java array as defined by
	 *            <cite>The Java&trade; Virtual Machine Specification</cite>.
	 * @throws IllegalArgumentException
	 *             If the expression is <tt>false</tt>.
	 */
	public static void checkArgument(boolean expression, String errorMessage,
			Object... errorArguments) {
		if (!expression) {
			if (errorMessage != null) {
				throw new IllegalArgumentException(format(errorMessage,
						errorArguments));
			}
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Ensures the truth of an expression involving the state of the calling
	 * instance, but not involving any parameters to the calling method.
	 * 
	 * @param expression
	 *            The representative expression of the state, as a
	 *            <tt>boolean</tt>.
	 * @throws IllegalStateException
	 *             If the expression is <tt>false</tt>.
	 */
	public static void checkState(boolean expression) {
		checkState(expression, null);
	}

	/**
	 * Ensures the truth of an expression involving the state of the calling
	 * instance, but not involving any parameters to the calling method.
	 * 
	 * @param expression
	 *            The representative expression of the state, as a
	 *            <tt>boolean</tt>.
	 * @param errorMessage
	 *            The <i>format string</i> message to use if the check fails.
	 * @param errorArguments
	 *            Arguments referenced by the format specifiers in the format
	 *            string. If there are more arguments than format specifiers,
	 *            the extra arguments are ignored. The number of arguments is
	 *            variable and may be zero. The maximum number of arguments is
	 *            limited by the maximum dimension of a Java array as defined by
	 *            <cite>The Java&trade; Virtual Machine Specification</cite>.
	 * @throws IllegalStateException
	 *             If the expression is <tt>false</tt>.
	 */
	public static void checkState(boolean expression, String errorMessage,
			Object... errorArguments) {
		if (!expression) {
			if (errorMessage != null) {
				throw new IllegalStateException(format(errorMessage,
						errorArguments));
			}
			throw new IllegalStateException();
		}
	}

	/**
	 * <tt>Preconditions</tt> is a static-utility class and should therefore
	 * never be constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private Preconditions() {
		super();
	}

}