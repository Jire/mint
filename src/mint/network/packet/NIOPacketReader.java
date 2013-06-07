package mint.network.packet;

import java.nio.ByteBuffer;

public final class NIOPacketReader extends NIOPacketBuffer implements
		PacketReader {

	public NIOPacketReader(ByteBuffer backing) {
		super(backing);
	}

	public NIOPacketReader(int capacity) {
		super(capacity);
	}

	public NIOPacketReader(byte[] data) {
		super(data);
	}

	public NIOPacketReader(Packet packet) {
		super(packet);
	}

	@Override
	public int read() {
		return readByte();
	}

	@Override
	public boolean readBoolean() {
		return read() > 0;
	}

	@Override
	public byte readByte() {
		return getBacking().get();
	}

	@Override
	public char readChar() {
		return getBacking().getChar();
	}

	@Override
	public short readShort() {
		return getBacking().getShort();
	}

	@Override
	public int readInt() {
		return getBacking().getInt();
	}

	@Override
	public long readLong() {
		return getBacking().getLong();
	}

	@Override
	public float readFloat() {
		return getBacking().getFloat();
	}

	@Override
	public double readDouble() {
		return getBacking().getDouble();
	}

	@Override
	public String readString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < readInt(); i++)
			builder.append(readChar());
		return builder.toString();
	}

}