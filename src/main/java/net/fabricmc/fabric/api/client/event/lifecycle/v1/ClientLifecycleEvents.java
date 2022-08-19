package net.fabricmc.fabric.api.client.event.lifecycle.v1;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ClientLifecycleEvents {
    public static List<ClientStarted> onClientStarted = new ArrayList<>();
    public static final Event<ClientStarted> CLIENT_STARTED = new ClientEvent();

    public static class ClientEvent extends Event<ClientStarted> {
        public void register(ClientStarted listener){
            onClientStarted.add(listener);
        }
    }

    public interface ClientStarted {
        void onClientStarted(MinecraftClient client);
    }
}
