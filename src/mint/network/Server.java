package mint.network;

import java.util.Set;

import mint.network.packet.PacketDecoder;
import mint.network.packet.PacketEncoder;
import mint.network.packet.PacketService;
import mint.service.Service;

public interface Server extends Service {

	int getPort();

	PacketService getPacketService();

	PacketEncoder getPacketEncoder();

	PacketDecoder getPacketDecoder();

	Set<Client> getClients();

}