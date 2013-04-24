package mint.plugin;

import mint.Nullable;
import mint.Serial;

public abstract class PluginException extends Exception {

	@Serial(9132299150724748285L)
	private static final long serialVersionUID = 9132299150724748285L;

	private final Plugin plugin;

	protected PluginException(@Nullable Plugin plugin) {
		super();

		this.plugin = plugin;
	}

	protected PluginException(@Nullable Plugin plugin, String message) {
		super(message);

		this.plugin = plugin;
	}

	protected final Plugin getPlugin() {
		return plugin;
	}

}