// This file is part of ForgedFabric, Copyright LukeGrahamLandry, available under the terms of the GNU Lesser General Public License
// https://github.com/LukeGrahamLandry/ForgedFabric/blob/1.19/LICENSE.txt
package ca.lukegrahamlandry.forgedfabric.events;


import ca.lukegrahamlandry.forgedfabric.util.FFPlatformHelper;
import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FabricOnForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeModEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void register(RegistryEvent.Register<Block> event){
        FFPlatformHelper.init();
    }
}
