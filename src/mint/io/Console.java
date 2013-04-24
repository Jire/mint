package mint.io;

import java.io.IOError;
import java.util.IllegalFormatException;

import mint.inject.ImplementedBy;

/**
 * Used to access the character-based console device.
 * 
 * @author Thomas G. P. Nappo
 */
@ImplementedBy(UniversalConsole.class)
public interface Console {

	/**
	 * Prints a string. If the argument is null then the string "null" is
	 * printed. Otherwise, the string's characters are converted into bytes
	 * according to the platform's default character encoding.
	 * 
	 * @param text
	 *            The text to be printed.
	 */
	public void print(String text);

	/**
	 * Prints some text and then terminate the line.
	 * 
	 * @param text
	 *            The text to be printed.
	 */
	public void println(String text);

	/**
	 * Terminates the current line by writing the line separator string. The
	 * line separator string is defined by the system property
	 * <code>line.separator</code>, and is not necessarily a single newline
	 * character (<code>'\n'</code>).
	 */
	public void println();

	/**
	 * A convenience method to write a formatted string to this output stream
	 * using the specified format string and arguments.
	 * 
	 * <p>
	 * An invocation of this method of the form <tt>out.printf(format,
	 * args)</tt> behaves in exactly the same way as the invocation
	 * 
	 * <pre>
	 * out.format(format, args)
	 * </pre>
	 * 
	 * @param format
	 *            The format string to use.
	 * 
	 * @param args
	 *            Arguments referenced by the format specifiers in the format
	 *            string. If there are more arguments than format specifiers,
	 *            the extra arguments are ignored. The number of arguments is
	 *            variable and may be zero. The maximum number of arguments is
	 *            limited by the maximum dimension of a Java array as defined by
	 *            <cite>The Java&trade; Virtual Machine Specification</cite>.
	 * 
	 * @throws IllegalFormatException
	 *             If a format string contains an illegal syntax, a format
	 *             specifier that is incompatible with the given arguments,
	 *             insufficient arguments given the format string, or other
	 *             illegal conditions.
	 * 
	 * @throws NullPointerException
	 *             If the <tt>format</tt> is <tt>null</tt>
	 */
	public void printf(String format, Object... args);

	/**
	 * Reads a single line of text from the console.
	 * 
	 * @throws IOError
	 *             If an I/O error occurs.
	 * 
	 * @return A string containing the line read from the console, not including
	 *         any line-termination characters, or <tt>null</tt> if an end of
	 *         stream has been reached.
	 */
	public String readLine();

	/**
	 * Provides a prompt and then reads a single line of text from the console.
	 * 
	 * @param prompt
	 *            The string prompt context.
	 * 
	 * @return A string containing the line read from the console, not including
	 *         any line-termination characters, or <tt>null</tt> if an end of
	 *         stream has been reached.
	 */
	public String readLine(String prompt);

	/**
	 * Reads a password or passphrase from the console with (if possible)
	 * echoing disabled.
	 * 
	 * @throws IOError
	 *             If an I/O error occurs.
	 * 
	 * @return A {@link String} containing the password or passphrase read from
	 *         the console, not including any line-termination characters, or
	 *         <tt>null</tt> if an end of stream has been reached.
	 */
	public String readPassword();

	/**
	 * Provides a prompt and then reads a password or passphrase from the
	 * console with (if possible) echoing disabled.
	 * 
	 * @throws IOError
	 *             If an I/O error occurs.
	 * 
	 * @return A {@link String} containing the password or passphrase read from
	 *         the console, not including any line-termination characters, or
	 *         <tt>null</tt> if an end of stream has been reached.
	 */
	public String readPassword(String prompt);

}