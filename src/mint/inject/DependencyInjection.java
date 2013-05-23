package mint.inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mint.NotConstructable;
import mint.reflect.Classes;

/**
 * Your entry point to <i>Mint</i>'s dependency injection framework. Creates
 * {@link Injector}s from {@link Module}s.
 * 
 * @author Thomas G. P. Nappo
 */
public final class DependencyInjection extends NotConstructable {

	private static Injector noModuleInjector;

	/**
	 * Creates an injector with no modules.
	 * 
	 * @return An injector instance with no modules.
	 */
	public static Injector createInjector() {
		if (noModuleInjector != null) {
			return noModuleInjector;
		}
		// Unfortunately, I must specify a module
		return noModuleInjector = createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
			}
		});
	}

	/**
	 * Creates an injector for the given set of modules.
	 * 
	 * @param modules
	 *            The modules to use to build the injector.
	 * @return An injector instance for the given set of modules.
	 */
	public static Injector createInjector(Module... modules) {
		List<Module> productModules = new ArrayList<Module>(
				Arrays.asList(modules));
		List<Class<?>> singletons = new ArrayList<Class<?>>();
		List<Class<?>> implementedBys = new ArrayList<Class<?>>();
		for (Class<?> clazz : Classes.list()) {
			if (clazz.isAnnotationPresent(Singleton.class))
				singletons.add(clazz);
			if (clazz.isAnnotationPresent(ImplementedBy.class))
				implementedBys.add(clazz);
		}
		for (final Class<?> clazz : singletons) {
			productModules.add(new AbstractModule() {
				@Override
				protected void configure() {
					bind(clazz).to(null).in(Scope.SINGLETON);
				}
			});
		}
		for (final Class<?> clazz : implementedBys) {
			productModules.add(new AbstractModule() {
				@Override
				@SuppressWarnings({ "unchecked", "rawtypes" })
				protected void configure() {
					bind(clazz).to(
							(Class) clazz.getAnnotation(ImplementedBy.class)
									.value());
				}
			});
		}
		Collections.reverse(productModules); // prioritizes modules
		return new UniversalInjector(productModules.toArray(modules));
	}

	/**
	 * <tt>DependencyInjection</tt> is a static-utility class and should
	 * therefore never be constructed.
	 * 
	 * @throws UnsupportedOperationException
	 *             If construction occurs.
	 */
	private DependencyInjection() {
		super();
	}

}