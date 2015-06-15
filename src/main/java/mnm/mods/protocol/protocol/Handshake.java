package mnm.mods.protocol.protocol;

import java.io.IOException;

import mnm.mods.protocol.interfaces.PacketWrite;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.C00Handshake;

public class Handshake implements PacketWrite {

    private int target;

    public Handshake(int target) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Class<? extends Packet> getPacket() {
        return C00Handshake.class;
    }

    @Override
    public void writePacketData(PacketBuffer buffer) throws IOException {
        buffer.setByte(1, target);
    }

}