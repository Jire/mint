package mint.collect;

import java.util.Map;

import mint.Experimental;

@Experimental
public abstract class ForwardingMap<K, V> extends MapForwarder<Map<K, V>, K, V> {

	public ForwardingMap(Map<K, V> delegate) {
		super(delegate);
	}

}