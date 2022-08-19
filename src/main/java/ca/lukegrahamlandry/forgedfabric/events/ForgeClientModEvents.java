package ca.lukegrahamlandry.forgedfabric.events;


import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FabricOnForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientModEvents {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event){
        KeyBindingHelper.keys.forEach(event::register);
    }

    // make sure this fires after the one in the main mod
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onClientSetup(FMLClientSetupEvent event){
        System.out.println("clientevent lib");
        ClientLifecycleEvents.onClientStarted.forEach((action) -> action.onClientStarted(MinecraftClient.getInstance()));
    }
}
