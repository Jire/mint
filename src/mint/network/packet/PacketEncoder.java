package mint.network.packet;

public interface PacketEncoder {

	PacketWriter encode(Packet packet);

}