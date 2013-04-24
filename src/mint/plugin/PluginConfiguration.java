package mint.plugin;

import java.util.Collections;
import java.util.TreeMap;

public final class PluginConfiguration {

	private final TreeMap<String, String> map;

	public PluginConfiguration(TreeMap<String, String> map) {
		this.map = map;
	}

	public PluginConfiguration() {
		this(new TreeMap<String, String>(Collections.reverseOrder()));
	}

	public PluginConfiguration(PluginConfiguration configuration) {
		this(configuration.getMap());
	}

	synchronized TreeMap<String, String> getMap() {
		return map;
	}

	public String get(String key) {
		return getMap().get(key);
	}

	public byte getByte(String key) {
		return Byte.parseByte(get(key));
	}

	public short getShort(String key) {
		return Short.parseShort(get(key));
	}

	public char getCharacter(String key) {
		return get(key).charAt(0);
	}

	public int getInteger(String key) {
		return Integer.parseInt(get(key));
	}

	public long getLong(String key) {
		return Long.parseLong(get(key));
	}

	public float getFloat(String key) {
		return Float.parseFloat(get(key));
	}

	public double getDouble(String key) {
		return Double.parseDouble(get(key));
	}

	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}

	public PluginConfiguration put(String key, String value) {
		getMap().put(key, value);
		return this;
	}

	public PluginConfiguration putByte(String key, byte value) {
		return put(key, Byte.toString(value));
	}

	public PluginConfiguration putShort(String key, short value) {
		return put(key, Short.toString(value));
	}

	public PluginConfiguration putCharacter(String key, char value) {
		return put(key, Character.toString(value));
	}

	public PluginConfiguration putInteger(String key, int value) {
		return put(key, Integer.toString(value));
	}

	public PluginConfiguration putLong(String key, long value) {
		return put(key, Long.toString(value));
	}

	public PluginConfiguration putFloat(String key, float value) {
		return put(key, Float.toString(value));
	}

	public PluginConfiguration putDouble(String key, double value) {
		return put(key, Double.toString(value));
	}

	public PluginConfiguration putBoolean(String key, boolean value) {
		return put(key, Boolean.toString(value));
	}

}