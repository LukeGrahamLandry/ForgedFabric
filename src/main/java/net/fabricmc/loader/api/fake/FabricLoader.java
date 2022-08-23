package net.fabricmc.loader.api.fake;

import net.fabricmc.loader.impl.FabricLoaderImpl;

public interface FabricLoader {
    static FabricLoader getInstance(){
        return FabricLoaderImpl.I;
    }

    boolean isModLoaded(String modid);
}
