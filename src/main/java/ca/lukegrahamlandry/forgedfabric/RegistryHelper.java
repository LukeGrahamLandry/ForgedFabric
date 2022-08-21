package ca.lukegrahamlandry.forgedfabric;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;

public class RegistryHelper {
//    private static Map<String, DeferredRegister> registries = new HashMap<>();
//    public static Map<Identifier, IForgeRegistryEntry> stuff = new HashMap<>();
//
//    public static <T extends IForgeRegistryEntry<T>> void register(Registry<? super T> registry, Identifier id, Object entry){
//        // getRegistry(id, entry).register(id.getPath(), () -> entry); // TODO
//        stuff.put(id, (IForgeRegistryEntry) entry);
//
//    }
//
//    public static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> getRegistry(Identifier id, T entry){
//        String descriptor = id.getNamespace() + "_" + entry.getRegistryType().getName();
//        if (!registries.containsKey(descriptor)) {
//            registries.put(descriptor, DeferredRegister.create(entry.getRegistryType(), id.getNamespace()));
//        }
//        return registries.get(descriptor);
//    }
//
//    // TODO: when to call this
//    public static void init(){
//        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        for (DeferredRegister register : registries.values()){
//            register.register(bus);
//        }
//    }
}
