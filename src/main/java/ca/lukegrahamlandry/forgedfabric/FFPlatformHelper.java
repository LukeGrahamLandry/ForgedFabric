package ca.lukegrahamlandry.forgedfabric;

import ca.lukegrahamlandry.forgedfabric.util.RegistryHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFPlatformHelper {
    public static <T> T register(Registry<? super T> registry, Identifier id, T entry){
        return RegistryHelper.register(registry, id, entry);
    }
}
