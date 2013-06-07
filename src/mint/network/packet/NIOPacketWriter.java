package mint.network.packet;

import java.nio.ByteBuffer;

public final class NIOPacketWriter extends NIOPacketBuffer implements
		PacketWriter {

	public NIOPacketWriter(ByteBuffer backing) {
		super(backing);
	}

	public NIOPacketWriter(int capacity) {
		super(capacity);
	}

	public NIOPacketWriter(byte[] data) {
		super(data);
	}

	public NIOPacketWriter(Packet packet) {
		super(packet);
	}

	@Override
	public void write(int value) {
		getBacking().put((byte) value);
	}

	@Override
	public void writeBoolean(boolean value) {
		write(value ? 1 : 0);
	}

	@Override
	public void writeByte(byte value) {
		write(value);
	}

	@Override
	public void writeChar(char value) {
		getBacking().putChar(value);
	}

	@Override
	public void writeShort(short value) {
		getBacking().putShort(value);
	}

	@Override
	public void writeInt(int value) {
		getBacking().putInt(value);
	}

	@Override
	public void writeLong(long value) {
		getBacking().putLong(value);
	}

	@Override
	public void writeFloat(float value) {
		getBacking().putFloat(value);
	}

	@Override
	public void writeDouble(double value) {
		getBacking().putDouble(value);
	}

	@Override
	public void writeString(String value) {
		writeInt(value.length());
		for (char c : value.toCharArray())
			writeChar(c);
	}

}