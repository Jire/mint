package mint.plugin;

import mint.InferFuture;
import mint.Nullable;
import mint.Serial;

public final class MultipleManifestPluginException extends PluginException {

	@Serial(3087909515343463125L)
	private static final long serialVersionUID = 3087909515343463125L;

	protected MultipleManifestPluginException(@Nullable Plugin plugin) {
		super(plugin);
	}

	@InferFuture
	protected MultipleManifestPluginException(@Nullable Plugin plugin,
			String message) {
		super(plugin);
	}

}