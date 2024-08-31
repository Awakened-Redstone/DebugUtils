package io.github.flemmli97.debugutils.fabric;

import io.github.flemmli97.debugutils.DebugCommands;
import io.github.flemmli97.debugutils.DebugToggles;
import io.github.flemmli97.debugutils.network.S2CDebugToggle;
import io.github.flemmli97.debugutils.network.S2CSpawnChunk;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class DebugUtilsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, reg, dedicated) -> DebugCommands.register(dispatcher)));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> DebugToggles.onLogout(handler.getPlayer()));

        PayloadTypeRegistry.playS2C().register(S2CDebugToggle.TYPE, S2CDebugToggle.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(S2CSpawnChunk.TYPE, S2CSpawnChunk.STREAM_CODEC);
    }
}
