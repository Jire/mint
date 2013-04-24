package mint.inject;

import java.lang.reflect.Constructor;

import mint.Predicate;

/**
 * This predicate evaluates {@link Constructor}s to be in accord with the
 * injectable constructor specification: The constructor must be annotated with
 * {@code @Inject} or have no arguments.
 * 
 * @author Thomas G. P. Nappo
 */
@Singleton
public final class InjectableConstructorPredicate implements
		Predicate<Constructor<?>> {

	/**
	 * <tt>InjectableConstructorPredicate</tt> is a singleton and should
	 * therefore only be constructed once.
	 */
	private InjectableConstructorPredicate() {
	}

	@Override
	public boolean evaluate(Constructor<?> input) {
		return input.isAnnotationPresent(Inject.class)
				|| input.getParameterTypes().length == 0;
	}

	/**
	 * The once-constructed <tt>InjectableConstructorPredicate</tt> singleton
	 * instance.
	 */
	private static final InjectableConstructorPredicate INSTANCE = new InjectableConstructorPredicate();

	/**
	 * Returns the <tt>InjectableConstructorPredicate</tt> singleton instance.
	 * 
	 * @return The <tt>InjectableConstructorPredicate</tt> singleton instance.
	 */
	public static InjectableConstructorPredicate instance() {
		return INSTANCE;
	}

}