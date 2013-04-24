package mint.inject;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import mint.Experimental;

@Singleton
@Experimental
final class UniversalBinder implements Binder {

	private final Map<Class<?>, ImplementationBinding<?>> implementationMap = new HashMap<Class<?>, ImplementationBinding<?>>();
	private final Map<Class<?>, Map<Class<? extends Annotation>, Object>> constantMap = new HashMap<Class<?>, Map<Class<? extends Annotation>, Object>>();
	private final Map<Class<?>, Object> singletonMap = new HashMap<Class<?>, Object>();

	@Override
	public <T> void bindImplementation(ImplementationBinding<T> binding) {
		implementationMap.put(binding.getDefinition(), binding);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> ImplementationBinding<T> getBinding(Class<T> definition) {
		return (ImplementationBinding<T>) implementationMap.get(definition);
	}

	@Override
	public <T> void bindConstant(ConstantBinding<T> binding) {
		if (!constantMap.containsKey(binding.getType())) {
			constantMap.put(binding.getType(),
					new HashMap<Class<? extends Annotation>, Object>());
		}
		constantMap.get(binding.getType()).put(binding.getAnnotation(),
				binding.getValue());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getConstant(Class<T> type,
			Class<? extends Annotation> annotation) {
		return (T) constantMap.get(type).get(annotation);
	}

	@Override
	public void install(Module module) {
		module.configure(this);
	}

	@Override
	public boolean doesImplementationExist(Class<?> definition) {
		ImplementationBinding<?> binding = implementationMap.get(definition);
		return implementationMap != null && binding.getImplementation() != null;
	}

	@Override
	public boolean doesConstantExist(Class<?> type,
			Class<? extends Annotation> annotation) {
		return constantMap.get(type) != null
				&& constantMap.get(type).get(annotation) != null;
	}

	@Override
	public <T> void bindSingleton(Class<?> type, Object value) {
		singletonMap.put(type, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getSingleton(Class<?> type) {
		return (T) singletonMap.get(type);
	}

	@Override
	public boolean doesSingletonExist(Class<?> type) {
		return singletonMap.get(type) != null;
	}

}