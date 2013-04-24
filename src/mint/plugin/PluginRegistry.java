package mint.plugin;

import java.util.HashMap;
import java.util.Map;

public final class PluginRegistry {

	private final Map<PluginManifest, Plugin> registry;

	public PluginRegistry(Map<PluginManifest, Plugin> plugins) {
		this.registry = plugins;
	}

	public PluginRegistry() {
		this(new HashMap<PluginManifest, Plugin>());
	}

	public synchronized Map<PluginManifest, Plugin> accessRegistry() {
		return registry;
	}

	public void registerPlugin(PluginManifest manifest, Plugin plugin) {
		accessRegistry().put(manifest, plugin);
	}

	public Plugin getPlugin(PluginManifest manifest) {
		return accessRegistry().get(manifest);
	}

}