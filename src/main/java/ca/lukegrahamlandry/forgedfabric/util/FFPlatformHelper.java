// This file is part of ForgedFabric, Copyright LukeGrahamLandry, available under the terms of the GNU Lesser General Public License
// https://github.com/LukeGrahamLandry/ForgedFabric/blob/1.19/LICENSE.txt
package ca.lukegrahamlandry.forgedfabric.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;

public class FFPlatformHelper {
    private static Map<String, DeferredRegister> registries = new HashMap<>();

    public static <T> T register(Registry<? super T> registry, Identifier id, T entry){
        if (!(entry instanceof IForgeRegistryEntry)) {
            System.out.println(entry.toString() + " does not implement IForgeRegistryEntry");
            Registry.register(registry, id, entry);
        }

        getRegistry(id.getNamespace(), ((IForgeRegistryEntry)(entry)).getRegistryType()).register(id.getPath(), () -> entry);
        return entry;
    }

    public static DeferredRegister getRegistry(String modid, Class registryType){
        String descriptor = modid + "_" + registryType.getName();
        if (!registries.containsKey(descriptor)) {
            registries.put(descriptor, DeferredRegister.create(registryType, modid));
        }
        return registries.get(descriptor);
    }

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        for (DeferredRegister register : registries.values()){
            register.register(bus);
        }
    }
}
