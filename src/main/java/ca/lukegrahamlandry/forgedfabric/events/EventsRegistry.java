package ca.lukegrahamlandry.forgedfabric.events;

import net.minecraftforge.eventbus.api.IEventBus;

public class EventsRegistry {
    public static void registerForgeEvents(IEventBus bus){
        bus.register(ForgeEvents.class);
    }

    public static void registerModEvents(IEventBus bus){
        bus.register(ForgeModEvents.class);
    }

    public static void registerClientModEvents(IEventBus bus){
        bus.register(ForgeClientModEvents.class);
    }

    public static void registerClientForgeEvents(IEventBus bus){
        bus.register(ForgeClientEvents.class);
    }
}
