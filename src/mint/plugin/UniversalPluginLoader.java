package mint.plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import mint.Nullable;

public final class UniversalPluginLoader extends AbstractPluginLoader {

	@Override
	@Nullable
	@SuppressWarnings("unchecked")
	public Plugin load(File file) {
		if (file.isDirectory()
				|| !file.getName().toLowerCase().endsWith(".jar")) {
			return null;
		}

		Class<? extends Plugin> pluginClass = null;

		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL",
					URL.class);
			method.setAccessible(true);
			method.invoke((URLClassLoader) ClassLoader.getSystemClassLoader(),
					file.toURI().toURL());

			ZipFile zf = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry e = entries.nextElement();
				if (e.getName().toLowerCase().endsWith(".class")) {
					Class<? extends Plugin> potentialClass = (Class<? extends Plugin>) Class
							.forName(e.getName()
									.substring(0, e.getName().length() - 6)
									.replace("/", "."));
					if (potentialClass
							.isAnnotationPresent(PluginManifest.class)) {
						if (pluginClass != null) {
							throw new MultipleManifestPluginException();
						}

						pluginClass = potentialClass;
					}
				}
			}
			zf.close();

			if (pluginClass == null) {
				throw new NoManifestPluginException();
			}

			return pluginClass.newInstance();
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

}