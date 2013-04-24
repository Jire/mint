package mint.collect;

import java.util.Collection;

import mint.Experimental;

@Experimental
public abstract class ForwardingCollection<E> extends
		CollectionForwarder<Collection<E>, E> {

	public ForwardingCollection(Collection<E> delegate) {
		super(delegate);
	}

}