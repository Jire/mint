package mint.network;

import java.util.HashSet;
import java.util.Set;

import mint.network.packet.PacketDecoder;
import mint.network.packet.PacketEncoder;
import mint.network.packet.PacketService;
import mint.service.AbstractService;

public abstract class AbstractServer extends AbstractService implements Server {

	private final int port;

	private final PacketService packetService;
	private final PacketEncoder packetEncoder;
	private final PacketDecoder packetDecoder;

	private final Set<Client> clients;

	protected AbstractServer(int port, PacketService packetService,
			PacketEncoder packetEncoder, PacketDecoder packetDecoder,
			Set<Client> clients) {
		this.port = port;
		this.packetService = packetService;
		this.packetEncoder = packetEncoder;
		this.packetDecoder = packetDecoder;
		this.clients = clients;
	}

	protected AbstractServer(int port, PacketService packetService,
			PacketEncoder packetEncoder, PacketDecoder packetDecoder) {
		this(port, packetService, packetEncoder, packetDecoder,
				new HashSet<Client>());
	}

	@Override
	public final int getPort() {
		return port;
	}

	@Override
	public final synchronized PacketService getPacketService() {
		return packetService;
	}

	@Override
	public final PacketEncoder getPacketEncoder() {
		return packetEncoder;
	}

	@Override
	public final PacketDecoder getPacketDecoder() {
		return packetDecoder;
	}

	public final synchronized Set<Client> getClients() {
		return clients;
	}

}
