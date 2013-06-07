package mint.network.packet;

public interface PacketDecoder {

	Packet decode(PacketReader reader);

}