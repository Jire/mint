package mint.reflect;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import mint.Experimental;
import mint.NotConstructable;

/**
 * Static utility methods that allow you to reflect on the classes of the build
 * path.
 * 
 * @author Thomas G. P. Nappo
 */
@Experimental
public final class Classes extends NotConstructable {

	/**
	 * Searches through all of the classes of the build path and returns a list
	 * containing them.
	 * 
	 * @return A list of all the classes of the build path.
	 */
	public static List<Class<?>> list() {
		return listInside("");
	}

	private static List<Class<?>> listInside(String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String path = packageName.replace('.', '/');
		Enumeration<URL> roots;
		try {
			roots = Thread.currentThread().getContextClassLoader()
					.getResources(path);
		} catch (IOException e) {
			return classes;
		}
		while (roots.hasMoreElements()) {
			File root = new File(roots.nextElement().getPath());
			classes.addAll(listInside(root, packageName));
		}
		return classes;
	}

	private static List<Class<?>> listInside(File directory, String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				classes.addAll(listInside(file,
						packageName + (!packageName.isEmpty() ? "." : "")
								+ file.getName()));
			} else if (file.getName().endsWith(".class")) {
				try {
					classes.add(Class.forName(packageName
							+ '.'
							+ file.getName().substring(0,
									file.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					continue;
				}
			}
		}
		return classes;
	}

	public static List<Class<?>> all() {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		List<String> classNames = new ArrayList<String>();
		try {
			classNames.addAll(getClassNamesFromPackage(""));
		} catch (IOException e) {
		}
		for (String className : classNames) {
			try {
				classes.add(Class.forName(className));
			} catch (ClassNotFoundException e) {
			}
		}
		return classes;
	}

	public static List<String> getClassNamesFromPackage(String packageName)
			throws IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		List<String> names = new ArrayList<String>();
		packageName = packageName.replace(".", "/");
		URL packageURL = classLoader.getResource(packageName);
		if (packageURL.getProtocol().equals("jar")) {
			String jarFileName;
			JarFile jf;
			Enumeration<JarEntry> jarEntries;
			String entryName;
			jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
			jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
			jf = new JarFile(jarFileName);
			jarEntries = jf.entries();
			while (jarEntries.hasMoreElements()) {
				entryName = jarEntries.nextElement().getName();
				if (entryName.startsWith(packageName)
						&& entryName.length() > packageName.length() + 5) {
					entryName = entryName.substring(packageName.length(),
							entryName.lastIndexOf('.'));
					names.add(entryName);
				}
			}
		} else {
			File folder = new File(packageURL.getFile());
			File[] contenuti = folder.listFiles();
			String entryName;
			for (File actual : contenuti) {
				entryName = actual.getName();
				int lastIndex = entryName.lastIndexOf('.');
				if (lastIndex != -1)
					entryName = entryName.substring(0, lastIndex);
				names.add(entryName);
			}
		}
		return names;
	}

	/**
	 * <tt>Classes</tt> is a static-utility class and should therefore never be
	 * constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private Classes() {
		super();
	}

}