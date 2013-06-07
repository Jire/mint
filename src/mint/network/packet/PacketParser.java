package mint.network.packet;

public interface PacketParser {

	PacketRepresentation parse(Packet packet);

}