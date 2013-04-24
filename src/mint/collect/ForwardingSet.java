package mint.collect;

import java.util.Set;

import mint.Experimental;

@Experimental
public class ForwardingSet<E> extends CollectionForwarder<Set<E>, E> implements
		Set<E> {

	public ForwardingSet(Set<E> delegate) {
		super(delegate);
	}

}