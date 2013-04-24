package mint.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mint.Experimental;
import mint.NotConstructable;

/**
 * Static utility methods that help with files.
 * 
 * @author Thomas G. P. Nappo
 */
@Experimental
public final class FileHelper extends NotConstructable {

	/**
	 * Reads then parses a configuration file into a {@link Configuration}
	 * object.
	 * 
	 * <p>
	 * The read configuration file is according to the <i>Standard Configuration
	 * Definition</i>. This reads that a configuration file should contain lines
	 * that separate keys from their values with the <tt>=</tt> symbol. Keys are
	 * on the left side of the symbol while values reside on the right side.
	 * 
	 * Comments are specified by starting a line with either <tt>#</tt> or
	 * <tt>;</tt>. Note that many standard configuration formats define the
	 * usage of <tt>#</tt> for comments as legacy and subsiding libraries likely
	 * have deprecated functions which parse such formats.
	 * </p>
	 * 
	 * @param file
	 *            The configuration file to read and parse.
	 * @return A configuration mechanism object that allows you to use the read
	 *         information.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 * @throws IOException
	 *             If an I/O exception occurs.
	 */
	public static Configuration readConfiguration(File file) throws IOException {
		Map<String, String> entries = new HashMap<String, String>();
		for (String line : readLines(file)) {
			if (!line.isEmpty() && !line.startsWith(";")
					&& !line.startsWith("#")) {
				String[] split = line.split("=");
				StringBuilder builder = new StringBuilder();
				for (int i = 1; i < split.length; i++) {
					builder.append(split[i]);
				}
				entries.put(split[0], builder.toString());
			}
		}
		return new Configuration(entries);
	}

	/**
	 * Reads then parses a configuration file into a {@link Configuration}
	 * object.
	 * 
	 * <p>
	 * The read configuration file is according to the <i>Standard Configuration
	 * Definition</i>. This reads that a configuration file should contain lines
	 * that separate keys from their values with the <tt>=</tt> symbol. Keys are
	 * on the left side of the symbol while values reside on the right side.
	 * 
	 * Comments are specified by starting a line with either <tt>#</tt> or
	 * <tt>;</tt>. Note that many standard configuration formats define the
	 * usage of <tt>#</tt> for comments as legacy and subsiding libraries likely
	 * have deprecated functions which parse such formats.
	 * </p>
	 * 
	 * @param file
	 *            The configuration file to read and parse.
	 * @return A configuration mechanism object that allows you to use the read
	 *         information.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 * @throws IOException
	 *             If an I/O exception occurs.
	 */
	public static Configuration readConfiguration(String file)
			throws IOException {
		return readConfiguration(fileFrom(file));
	}

	/**
	 * Reads the lines of a file and returns them in a {@link List}.
	 * 
	 * @param file
	 *            The file to read.
	 * @return A list containing the lines of the file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 * @throws IOException
	 *             If an I/O exception occurs.
	 */
	public static List<String> readLines(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = openReader(file);
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}

	/**
	 * Reads the lines of a file and returns them in a {@link List}.
	 * 
	 * @param file
	 *            The file to read.
	 * @return A list containing the lines of the file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 * @throws IOException
	 *             If an I/O exception occurs.
	 */
	public static List<String> readLines(String file) throws IOException {
		return readLines(fileFrom(file));
	}

	/**
	 * Opens a {@link BufferedReader} on a file.
	 * 
	 * @param file
	 *            The file to open the reader on.
	 * @return A <tt>BufferedReader</tt> mechanism used to read from the
	 *         specified file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 */
	public static BufferedReader openReader(File file)
			throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(openStream(file)));
	}

	/**
	 * Opens a {@link BufferedReader} on a file.
	 * 
	 * @param file
	 *            The file to open the reader on.
	 * @return A <tt>BufferedReader</tt> mechanism used to read from the
	 *         specified file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 */
	public static BufferedReader openReader(String file)
			throws FileNotFoundException {
		return openReader(fileFrom(file));
	}

	/**
	 * Opens an {@link InputStream} on a file.
	 * 
	 * @param file
	 *            The file to open the stream on.
	 * @return An <tt>InputStream</tt> mechanism used to read from the specified
	 *         file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 */
	public static InputStream openStream(File file)
			throws FileNotFoundException {
		return new FileInputStream(file);
	}

	/**
	 * Opens an {@link InputStream} on a file.
	 * 
	 * @param file
	 *            The file to open the stream on.
	 * @return An <tt>InputStream</tt> mechanism used to read from the specified
	 *         file.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 */
	public static InputStream openStream(String file)
			throws FileNotFoundException {
		return openStream(fileFrom(file));
	}

	/**
	 * Returns a new <tt>File</tt> instance by converting the given pathname
	 * string into an abstract pathname. If the given string is the empty
	 * string, then the result is the empty abstract pathname.
	 * 
	 * @param file
	 *            The pathname string to use.
	 * @return A new <tt>File</tt> instance created by converting the given
	 *         pathname string into an abstract pathname.
	 */
	public static File fileFrom(String file) {
		return new File(file);
	}

	/**
	 * <tt>FileHelper</tt> is a static-utility class and should therefore never
	 * be constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private FileHelper() {
		super();
	}

}