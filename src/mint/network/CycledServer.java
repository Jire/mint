package mint.network;

import mint.Stopwatch;
import mint.network.packet.PacketDecoder;
import mint.network.packet.PacketEncoder;
import mint.network.packet.PacketService;

public abstract class CycledServer extends AbstractServer {

	private final int cycleLength;

	protected CycledServer(int port, PacketService packetService,
			PacketEncoder packetEncoder, PacketDecoder packetDecoder,
			int cycleLength) {
		super(port, packetService, packetEncoder, packetDecoder);
		this.cycleLength = cycleLength;
	}

	public final int getCycleLength() {
		return cycleLength;
	}

	@Override
	protected final synchronized void onStart() {
		while (isRunning()) {
			Stopwatch stopwatch = Stopwatch.start();

			try {
				cycle();
			} catch (Exception e) {
				// Nowhere to propagate
				e.printStackTrace();
			}

			long elapsed = stopwatch.elapsed();
			if (elapsed < getCycleLength()) {
				try {
					Thread.sleep(getCycleLength() - elapsed);
				} catch (InterruptedException e) {
					// Immediately return if this thread was somehow interrupted
					e.printStackTrace();
					return;
				}
			}
		}
	}

	protected abstract void cycle() throws Exception;

}