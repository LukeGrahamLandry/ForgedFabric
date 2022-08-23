// This file is part of ForgedFabric, Copyright LukeGrahamLandry, available under the terms of the GNU Lesser General Public License
// https://github.com/LukeGrahamLandry/ForgedFabric/blob/1.19/LICENSE.txt
package ca.lukegrahamlandry.forgedfabric.network;

import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    // problematic for this to be called from multiple mods but when i move it into the loaded check in MOD_ID = "forgedfabric"#setup, it breaks
    public static void registerMessages(){
        INSTANCE = NetworkRegistry.newSimpleChannel(new Identifier(FabricOnForgeMod.MOD_ID, "network"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(ID++, PacketWrapper.class, PacketWrapper::encode, PacketWrapper::decode, PacketWrapper::handle);
    }
}
