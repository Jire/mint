package mint.plugin;

import java.io.File;

public final class UniversalPluginManager extends AbstractPluginManager {

	public UniversalPluginManager(PluginRegistry registry, PluginLoader loader,
			File directory) {
		super(registry, loader, directory);
	}

	public UniversalPluginManager(PluginRegistry registry, PluginLoader loader,
			String directory) {
		super(registry, loader, directory);
	}

	public UniversalPluginManager(PluginRegistry registry, PluginLoader loader) {
		super(registry, loader);
	}

	public UniversalPluginManager(PluginLoader loader) {
		super(loader);
	}

	public UniversalPluginManager() {
		this(new UniversalPluginLoader());
	}

}