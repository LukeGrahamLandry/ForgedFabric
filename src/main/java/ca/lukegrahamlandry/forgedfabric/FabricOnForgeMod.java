package ca.lukegrahamlandry.forgedfabric;

import ca.lukegrahamlandry.forgedfabric.network.NetworkHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(FabricOnForgeMod.MOD_ID)
public class FabricOnForgeMod {
    public static final String MOD_ID = "forgedfabric";
    public FabricOnForgeMod() {
        NetworkHandler.registerMessages();
    }
}
