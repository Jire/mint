package mint.network.packet;

public interface Packet {

	int getId();

	byte[] getData();

	int getLength();

}