package mint;

import java.lang.annotation.Annotation;

@Experimental
public final class Predicates {

	private static final class NotNullPredicate implements Predicate<Object> {

		private static final Predicate<?> instance = new NotNullPredicate();

		@Override
		public boolean evaluate(Object input) {
			return input != null;
		}

	}

	public static Predicate<?> notNull() {
		return NotNullPredicate.instance;
	}

	public static Predicate<Class<?>> annotatedWith(
			final Class<? extends Annotation> annotation) {
		return new Predicate<Class<?>>() {

			@Override
			public boolean evaluate(Class<?> input) {
				return input.isAnnotationPresent(annotation);
			}

		};
	}

}