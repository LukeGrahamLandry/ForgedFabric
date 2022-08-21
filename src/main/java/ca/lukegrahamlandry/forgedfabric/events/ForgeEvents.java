package ca.lukegrahamlandry.forgedfabric.events;


import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = FabricOnForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        if (!event.getEntity().world.isClient())
            ServerPlayConnectionEvents.onPlayerJoined.forEach((action) -> action.onPlayReady(
                ((ServerPlayerEntity)event.getEntity()).networkHandler,
                (id, data) -> ServerPlayNetworking.send((ServerPlayerEntity) event.getEntity(), id, data),
                event.getEntity().getServer()
        ));
    }

    @SubscribeEvent
    public static void onSererStart(FMLServerStartedEvent event){
        event.getServer().execute(() -> ServerLifecycleEvents.onServerStarted.forEach((action) -> action.onServerStarted(event.getServer())));
    }
}
