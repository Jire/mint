package mint.io;

import java.util.Scanner;

import mint.Experimental;
import mint.inject.Singleton;

/**
 * Used to access the character-based console device by making use of both
 * {@link java.io.Console} and {@link Scanner}; with preference to
 * <tt>java.io.Console</tt>.
 * 
 * @author Thomas G. P. Nappo
 */
@Singleton
@Experimental
public final class UniversalConsole implements Console {

	/**
	 * The backing <i>Java</i> console that is used with preference over
	 * {@link Scanner}.
	 */
	private final java.io.Console console;

	/**
	 * The backing <i>Java</i> scanner. Note that {@link #console} will take
	 * preference when possible.
	 */
	private final Scanner scanner;

	/**
	 * Constructs a new universal console.
	 * 
	 * @param console
	 *            The backing <i>Java</i> console that is used with preference
	 *            over {@link Scanner}.
	 * @param scanner
	 *            The backing <i>Java</i> scanner. Note that {@link #console}
	 *            will take preference when possible.
	 */
	public UniversalConsole(java.io.Console console, Scanner scanner) {
		this.console = console;
		this.scanner = scanner;
	}

	/**
	 * Constructs a new universal console; supplying {@link System#console} for
	 * the backing console and creates a new {@link Scanner} on
	 * {@link System#in}.
	 */
	public UniversalConsole() {
		this(System.console(), new Scanner(System.in));
	}

	@Override
	public void print(String text) {
		System.out.print(text);
	}

	@Override
	public void println(String text) {
		System.out.println(text);
	}

	@Override
	public void println() {
		println("");
	}

	@Override
	public void printf(String format, Object... args) {
		System.out.printf(format, args);
	}

	@Override
	public String readLine() {
		if (console == null) {
			return scanner.nextLine();
		}
		return console.readLine();
	}

	@Override
	public String readLine(String prompt) {
		print(prompt + " ");
		return readLine();
	}

	@Override
	public String readPassword() {
		if (console == null) {
			return readLine();
		}
		return String.valueOf(console.readPassword());
	}

	@Override
	public String readPassword(String prompt) {
		print(prompt + " ");
		return readPassword();
	}

}