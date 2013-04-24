package mint.plugin;

import java.io.File;
import java.util.Set;

public interface PluginManager {

	static final File DEFAULT_PLUGINS_DIRECTORY = new File("plugins"
			+ File.separator);

	PluginLoader getLoader();

	Set<Plugin> getPlugins();

	Plugin getPlugin(String name);

	Set<Plugin> loadPlugins(File directory);

	Set<Plugin> loadPlugins();

	void enablePlugins();

	void disablePlugins();

}