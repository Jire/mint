package mint.network.packet;

public interface PacketWriter {

	void write(int value);

	void writeBoolean(boolean value);

	void writeByte(byte value);

	void writeChar(char value);

	void writeShort(short value);

	void writeInt(int value);

	void writeLong(long value);

	void writeFloat(float value);

	void writeDouble(double value);

	void writeString(String value);

}