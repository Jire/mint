package mint.network.packet;

public interface PacketReader {

	int read();

	boolean readBoolean();

	byte readByte();

	char readChar();

	short readShort();

	int readInt();

	long readLong();

	float readFloat();

	double readDouble();

	String readString();

}