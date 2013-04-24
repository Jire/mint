package mint.plugin;

import java.io.File;

public abstract class AbstractPluginLoader implements PluginLoader {

	@Override
	public final Plugin load(String file) {
		return load(new File(file));
	}

}