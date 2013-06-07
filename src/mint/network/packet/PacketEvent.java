package mint.network.packet;

public abstract class PacketEvent {

	private final Packet packet;

	protected PacketEvent(Packet packet) {
		this.packet = packet;
	}

	public final Packet getPacket() {
		return packet;
	}

}