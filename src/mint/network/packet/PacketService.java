package mint.network.packet;

public interface PacketService extends PacketParser, PacketBuilder {

	void registerParser(PacketParser parser);

	void registerBuilder(PacketBuilder builder);

}