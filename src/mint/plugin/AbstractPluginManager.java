package mint.plugin;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPluginManager implements PluginManager {

	private final PluginRegistry registry;
	private final PluginLoader loader;
	private final File directory;

	protected AbstractPluginManager(PluginRegistry registry,
			PluginLoader loader, File directory) {
		this.registry = registry;
		this.loader = loader;
		this.directory = directory;
	}

	protected AbstractPluginManager(PluginRegistry registry,
			PluginLoader loader, String directory) {
		this(registry, loader, new File(directory));
	}

	protected AbstractPluginManager(PluginRegistry registry, PluginLoader loader) {
		this(registry, loader, DEFAULT_PLUGINS_DIRECTORY);
	}

	protected AbstractPluginManager(PluginLoader loader) {
		this(new PluginRegistry(), loader);
	}

	protected final PluginRegistry getRegistry() {
		return registry;
	}

	protected final File getDirectory() {
		return directory;
	}

	@Override
	public final PluginLoader getLoader() {
		return loader;
	}

	@Override
	public final Set<Plugin> getPlugins() {
		return Collections.unmodifiableSet(new HashSet<Plugin>(registry
				.accessRegistry().values()));
	}

	@Override
	public final Plugin getPlugin(String name) {
		for (Plugin plugin : getPlugins())
			if (plugin.getManifest().name().equalsIgnoreCase(name))
				return plugin;
		return null;
	}

	@Override
	public final Set<Plugin> loadPlugins(File directory) {
		Set<Plugin> plugins = new HashSet<Plugin>();
		for (File file : directory.listFiles()) {
			Plugin plugin = getLoader().load(file);
			if (plugin != null)
				plugins.add(plugin);
		}
		for (Plugin plugin : plugins)
			getRegistry().registerPlugin(plugin.getManifest(), plugin);
		return plugins;
	}

	@Override
	public final Set<Plugin> loadPlugins() {
		return loadPlugins(getDirectory());
	}

	@Override
	public final void enablePlugins() {
		for (Plugin plugin : getPlugins())
			plugin.enable();
	}

	@Override
	public final void disablePlugins() {
		for (Plugin plugin : getPlugins())
			plugin.disable();
	}

}