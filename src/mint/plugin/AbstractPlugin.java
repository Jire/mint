package mint.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class AbstractPlugin implements Plugin {
	
	private boolean enabled;

	@Override
	public final void enable() {
		if (isEnabled())
			throw new IllegalStateException("The plugin was already enabled!");
		enabled = true;
		onEnable();
	}

	@Override
	public final void disable() {
		if (!isEnabled())
			throw new IllegalStateException("The plugin is not enabled!");
		enabled = false;
		onDisable();
	}

	@Override
	public final boolean isEnabled() {
		return enabled;
	}

	@Override
	public final InputStream getResource(String file) {
		try {
			return new FileInputStream(new File(getResourceDirectory() + file));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public final PluginManifest getManifest() {
		return getClass().getAnnotation(PluginManifest.class);
	}

	@Override
	public final String getResourceDirectory() {
		return PluginManager.DEFAULT_PLUGINS_DIRECTORY.getName()
				+ File.separator + getManifest().name() + File.separator;
	}

	protected void onEnable() {
		// Intended to be overridden
	}

	protected void onDisable() {
		// Intended to be overridden
	}

	@Override
	public final PluginConfiguration loadConfiguration(File file) {
		TreeMap<String, String> entries = new TreeMap<String, String>();
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
		return new PluginConfiguration(entries);
	}

	@Override
	public final PluginConfiguration loadConfiguration(String file) {
		return loadConfiguration(fileFrom(file));
	}

	@Override
	public final void saveConfiguration(PluginConfiguration configuration,
			File file) {
		BufferedWriter writer = openWriter(file);
		try {
			for (Entry<String, String> entry : configuration.getMap()
					.entrySet()) {
				writer.write(entry.getKey() + "=" + entry.getValue());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
		}
	}

	@Override
	public final void saveConfiguration(PluginConfiguration configuration,
			String file) {
		saveConfiguration(configuration, fileFrom(file));
	}

	@Override
	public final List<String> readLines(File file) {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = openReader(file);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
		}
		return lines;
	}

	@Override
	public final List<String> readLines(String file) {
		return readLines(fileFrom(file));
	}

	@Override
	public final BufferedReader openReader(File file) {
		return new BufferedReader(new InputStreamReader(openInputStream(file)));
	}

	@Override
	public final BufferedReader openReader(String file) {
		return openReader(fileFrom(file));
	}

	@Override
	public final BufferedWriter openWriter(File file) {
		return new BufferedWriter(
				new OutputStreamWriter(openOutputStream(file)));
	}

	@Override
	public final BufferedWriter openWriter(String file) {
		return openWriter(fileFrom(file));
	}

	@Override
	public final InputStream openInputStream(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public final InputStream openInputStream(String file) {
		return openInputStream(fileFrom(file));
	}

	@Override
	public final OutputStream openOutputStream(File file) {
		try {
			if (!file.exists())
				file.getParentFile().mkdirs();
			return new FileOutputStream(file);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public final OutputStream openOutputStream(String file) {
		return openOutputStream(fileFrom(file));
	}

	@Override
	public final File fileFrom(String file) {
		return new File(getResourceDirectory() + file);
	}

}