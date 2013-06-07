package mint.network.packet;

import java.nio.ByteBuffer;

public abstract class NIOPacketBuffer implements PacketBuffer {

	private final ByteBuffer backing;

	public NIOPacketBuffer(ByteBuffer backing) {
		this.backing = backing;
	}

	public NIOPacketBuffer(int capacity) {
		this(ByteBuffer.allocate(capacity));
	}

	public NIOPacketBuffer(byte[] data) {
		this(data.length);
	}

	public NIOPacketBuffer(Packet packet) {
		this(packet.getData());
	}

	public final ByteBuffer getBacking() {
		return backing;
	}

}