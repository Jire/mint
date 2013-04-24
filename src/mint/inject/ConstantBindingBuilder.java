package mint.inject;

import java.lang.annotation.Annotation;

public final class ConstantBindingBuilder<T> {

	private final Binder binder;
	private final Class<T> type;
	private final Class<? extends Annotation> annotation;

	public ConstantBindingBuilder(Binder binder, Class<T> type,
			Class<? extends Annotation> annotation) {
		this.binder = binder;
		this.type = type;
		this.annotation = annotation;
	}

	public void to(T value) {
		binder.bindConstant(new ConstantBinding<T>(type, annotation, value));
	}

}