package ca.lukegrahamlandry.forgedfabric.events;


import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import ca.lukegrahamlandry.forgedfabric.RegistryHelper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Map;

@Mod.EventBusSubscriber(modid = FabricOnForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeModEvents {
//    @SubscribeEvent
//    public static void register(RegistryEvent.Register<SoundEvent> event){
//        for (Map.Entry<Identifier, IForgeRegistryEntry> entry : RegistryHelper.stuff.entrySet()){
//            if (entry.getValue().getRegistryType() == event.getRegistry().getRegistrySuperType()){
//                entry.getValue().setRegistryName(entry.getKey());
//                event.getRegistry().register((SoundEvent) entry.getValue());
//            }
//        }
//    }
}
