// This file is part of ForgedFabric, Copyright LukeGrahamLandry, available under the terms of the GNU Lesser General Public License
// https://github.com/LukeGrahamLandry/ForgedFabric/blob/1.19/LICENSE.txt
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
