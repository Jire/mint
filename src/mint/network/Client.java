package mint.network;

import mint.network.packet.Packet;
import mint.network.packet.PacketRepresentation;

public interface Client {

	Server getServer();

	void write(Packet packet);

	void write(PacketRepresentation packetRep);

	void disconnect();

}