package mint.inject;

public final class ScopedBindingBuilder<T> {

	private final Binder binder;
	private final ImplementationBinding<T> binding;

	public ScopedBindingBuilder(Binder binder, ImplementationBinding<T> binding) {
		this.binder = binder;
		this.binding = binding;
	}

	/**
	 * Changes the scope of the previously created binding.
	 * 
	 * @param scope
	 *            The new scope type.
	 */
	public void in(Scope scope) {
		binding.setScope(scope);
		if (scope.equals(Scope.SINGLETON))
			binder.bindSingleton(binding.getDefinition(), null);
	}

}