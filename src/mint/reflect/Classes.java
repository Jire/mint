package mint.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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
		List<Class<?>> all = listFS();
		all.addAll(listAllWithinPackage());
		return all;
	}

	private static List<Class<?>> listAllWithinPackage() {
		return listWithinPackage("");
	}

	private static List<Class<?>> listWithinPackage(String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		CodeSource src = Classes.class.getProtectionDomain().getCodeSource();
		if (src != null) {
			String jarName = src.getLocation().getFile();
			packageName = packageName.replaceAll("\\.", "/");

			File jar = new File(jarName);
			if (jar.exists()) {
				try {
					JarInputStream jarFile = new JarInputStream(
							new FileInputStream(jarName));

					JarEntry jarEntry;
					while ((jarEntry = jarFile.getNextJarEntry()) != null) {
						if ((jarEntry.getName().startsWith(packageName))
								&& (jarEntry.getName().endsWith(".class"))) {
							classes.add(Class.forName(jarEntry
									.getName()
									.replaceAll("/", "\\.")
									.substring(0,
											jarEntry.getName().length() - 6)));
						}
					}

					jarFile.close();
				} catch (IOException e) {
				} catch (ClassNotFoundException e) {
				}
			}
		}

		return classes;
	}

	private static List<Class<?>> listFS() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		CodeSource src = Classes.class.getProtectionDomain().getCodeSource();
		if (src != null
				&& !src.getLocation().getFile().toLowerCase().endsWith(".jar")) {
			list.addAll(listFSInside(""));
		}
		return list;
	}

	private static List<Class<?>> listFSInside(String packageName) {
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
				} catch (NoClassDefFoundError e) {
					continue;
				}
			}
		}
		return classes;
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