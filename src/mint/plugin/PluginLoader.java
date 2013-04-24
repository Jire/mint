package mint.plugin;

import java.io.File;

public interface PluginLoader {

	Plugin load(File file);

	Plugin load(String file);

}