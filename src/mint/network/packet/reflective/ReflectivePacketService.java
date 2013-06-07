package mint.network.packet.reflective;

import java.util.HashMap;
import java.util.Map;

import mint.network.packet.Packet;
import mint.network.packet.PacketBuilder;
import mint.network.packet.PacketParser;
import mint.network.packet.PacketRepresentation;
import mint.network.packet.PacketService;

public final class ReflectivePacketService implements PacketService {

	private final Map<Integer, PacketParser> parsers;
	private final Map<Class<? extends PacketRepresentation>, PacketBuilder> builders;

	private ReflectivePacketService(Map<Integer, PacketParser> parsers,
			Map<Class<? extends PacketRepresentation>, PacketBuilder> builders) {
		this.parsers = parsers;
		this.builders = builders;
	}

	public ReflectivePacketService() {
		this(
				new HashMap<Integer, PacketParser>(),
				new HashMap<Class<? extends PacketRepresentation>, PacketBuilder>());
	}

	@Override
	public PacketRepresentation parse(Packet packet) {
		PacketParser parser = parsers.get(packet.getId());
		if (parser != null)
			return parser.parse(packet);
		return null;
	}

	@Override
	public Packet build(PacketRepresentation packetRep) {
		PacketBuilder builder = builders.get(packetRep.getClass());
		if (builder != null)
			return builder.build(packetRep);
		return null;
	}

	@Override
	public void registerParser(PacketParser parser) {
		ParsesPacket annotation = parser.getClass().getAnnotation(
				ParsesPacket.class);
		if (annotation == null)
			throw new IllegalArgumentException(
					"Packet parsers must be annotated with @ParsesPacket");
		for (int packetId : annotation.value())
			parsers.put(packetId, parser);
	}

	@Override
	public void registerBuilder(PacketBuilder builder) {
		BuildsPacket annotation = builder.getClass().getAnnotation(
				BuildsPacket.class);
		if (annotation == null)
			throw new IllegalArgumentException(
					"Packet builders must be annotated with @BuildsPacket");
		builders.put(annotation.value(), builder);
	}

}