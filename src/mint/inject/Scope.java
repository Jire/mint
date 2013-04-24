package mint.inject;

/**
 * A scope is a level of visibility that instances provided by the injection
 * framework may have.
 * 
 * <p>
 * By default, an instance created by the {@link Injector} has <i>no scope</i>,
 * meaning it has no state from the framework's perspective -- the
 * {@code Injector} creates it, injects it once into the class that required it,
 * and then immediately forgets it. Associating a scope with a particular
 * binding allows the created instance to be "remembered" and possibly used
 * again for other injections.
 * </p>
 * 
 * @author Thomas G. P Nappo
 */
public enum Scope {

	/**
	 * No scope; the same as not applying any scope at all. Each time the
	 * {@link Injector} obtains an instance of an object with "no scope", it
	 * injects the instance then immediately forgets it. When the next request
	 * for the same binding arrives it will need to obtain the instance over
	 * again.
	 */
	DEFAULT,

	/**
	 * One instance per {@link Injector}.
	 * 
	 * @see {@link Singleton}.
	 */
	SINGLETON;

}