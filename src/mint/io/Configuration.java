package mint.io;

import java.util.HashMap;
import java.util.Map;

import mint.Experimental;
import mint.Nullable;

/**
 * A mechanism that provides functionality to read keys as specific types from a
 * map.
 * 
 * @author Thomas G. P. Nappo
 */
@Experimental
public final class Configuration {

	/**
	 * The backing map of the configuration. This map stores all of the keys and
	 * values of a configuration.
	 */
	private final Map<String, String> map;

	/**
	 * Constructs a new configuration mechanism.
	 * 
	 * @param map
	 *            The backing map of the configuration which stores all of the
	 *            keys and values of the configuration.
	 */
	public Configuration(Map<String, String> map) {
		this.map = map;
	}

	/**
	 * Constructs a new configuration mechanism with an empty {@link HashMap}.
	 */
	public Configuration() {
		this(new HashMap<String, String>());
	}

	/**
	 * Constructs a new configuration mechanism importing values from an
	 * existing configuration.
	 * 
	 * @param configuration
	 *            The configuration to import values from.
	 */
	public Configuration(Configuration configuration) {
		this(configuration.getMap());
	}

	/**
	 * Returns a map containing all of the keys and values of the configuration.
	 * 
	 * @return The configuration's {@link #map}.
	 */
	private Map<String, String> getMap() {
		return map;
	}

	/**
	 * Returns a <tt>string</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>string</tt>, or <tt>null</tt> if this configuration has not
	 *         been configured for the key.
	 */
	@Nullable
	public String get(String key) {
		return getMap().get(key);
	}

	/**
	 * Returns a <tt>byte</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>byte</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>byte</tt>.
	 */
	public byte getByte(String key) {
		return Byte.parseByte(get(key));
	}

	/**
	 * Returns a <tt>short</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>short</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>short</tt>.
	 */
	public short getShort(String key) {
		return Short.parseShort(get(key));
	}

	/**
	 * Returns a <tt>int</tt> value to which the specified key is configured to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>int</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>int</tt>.
	 */
	public int getInteger(String key) {
		return Integer.parseInt(get(key));
	}

	/**
	 * Returns a <tt>long</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>long</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>long</tt>.
	 */
	public long getLong(String key) {
		return Long.parseLong(get(key));
	}

	/**
	 * Returns a <tt>float</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>float</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>float</tt>.
	 */
	public float getFloat(String key) {
		return Float.parseFloat(get(key));
	}

	/**
	 * Returns a <tt>double</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>double</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>double</tt>.
	 */
	public double getDouble(String key) {
		return Double.parseDouble(get(key));
	}

	/**
	 * Returns a <tt>boolean</tt> value to which the specified key is configured
	 * to.
	 * 
	 * @param key
	 *            The key whose associated value is to be returned.
	 * @return The value to which the specified key is configured as a
	 *         <tt>boolean</tt>.
	 * @throws NumberFormatException
	 *             If the associated value could not be converted to a
	 *             <tt>boolean</tt>.
	 */
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}

}