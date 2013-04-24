package mint.collect;

import java.util.Collection;
import java.util.Iterator;

import mint.Experimental;
import mint.Forwarder;

@Experimental
public abstract class CollectionForwarder<S extends Collection<E>, E> extends
		Forwarder<S> implements Collection<E> {

	public CollectionForwarder(S delegate) {
		super(delegate);
	}

	@Override
	public int size() {
		return delegate().size();
	}

	@Override
	public boolean isEmpty() {
		return delegate().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return delegate().contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return delegate().iterator();
	}

	@Override
	public Object[] toArray() {
		return delegate().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return delegate().toArray(a);
	}

	@Override
	public boolean add(E e) {
		return delegate().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return delegate().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return delegate().addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return delegate().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return delegate().retainAll(c);
	}

	@Override
	public void clear() {
		delegate().clear();
	}

}