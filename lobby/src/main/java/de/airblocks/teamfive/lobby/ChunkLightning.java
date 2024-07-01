package de.airblocks.teamfive.lobby;

import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;

public class ChunkLightning {
    public static void setChunkSupplierForInstance(InstanceContainer instance) {
        instance.setChunkSupplier(LightingChunk::new);
    }
}
