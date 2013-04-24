package mint.inject;

import static mint.collect.Iterables.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import mint.Experimental;

@Singleton
@Experimental
final class UniversalInjector implements Injector {

	private final Binder binder;

	UniversalInjector(Binder binder, Module[] modules) {
		this.binder = binder;
		for (Module module : modules) {
			binder.install(module);
		}
	}

	UniversalInjector(Module[] modules) {
		this(new UniversalBinder(), modules);
	}

	@Override
	public Binder getBinder() {
		return binder;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> definition) {
		if (binder.doesSingletonExist(definition)) {
			return binder.getSingleton(definition);
		}

		ImplementationBinding<T> binding = new ImplementationBinding<T>(
				definition, definition);
		if (getBinder().doesImplementationExist(definition)) {
			binding = getBinder().getBinding(definition);
		}
		constructors: for (Constructor<?> constructor : filter(binding
				.getImplementation().getConstructors(),
				InjectableConstructorPredicate.instance())) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			Object[] parameters = new Object[parameterTypes.length];
			parameters: for (int i = 0; i < parameterTypes.length; i++) {
				Class<?> parameterType = parameterTypes[i];
				Annotation[] parameterAnnotations = constructor
						.getParameterAnnotations()[i];
				if (parameterAnnotations.length > 0) {
					for (Annotation parameterAnnotation : constructor
							.getParameterAnnotations()[i]) {
						Class<? extends Annotation> annotationType = parameterAnnotation
								.annotationType();
						if (!getBinder().doesConstantExist(parameterType,
								annotationType)) {
							break;
						}
						parameters[i] = getBinder().getConstant(parameterType,
								annotationType);
						continue parameters;
					}
				}
				Object instance = getInstance(parameterType);
				if (instance != null) {
					parameters[i] = instance;
					continue;
				}
				continue constructors;
			}
			T instance;
			try {
				instance = (T) constructor.newInstance(parameters);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if (binding.getScope().equals(Scope.SINGLETON)) {
				binder.bindSingleton(definition, instance);
			}
			return instance;
		}

		throw new NullPointerException();
	}

}