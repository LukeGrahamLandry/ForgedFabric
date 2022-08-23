// This file is part of ForgedFabric, Copyright LukeGrahamLandry, available under the terms of the GNU Lesser General Public License
// https://github.com/LukeGrahamLandry/ForgedFabric/blob/1.19/LICENSE.txt
package ca.lukegrahamlandry.forgedfabric.events;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.Event;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper {
    public static List<ItemTooltipCallback> onTooltip = new ArrayList<>();
    public static List<HudRenderCallback> onRenderHud = new ArrayList<>();

    public static class TooltipEvent extends Event<ItemTooltipCallback> {
        public void register(ItemTooltipCallback listener){
            onTooltip.add(listener);
        }
    }

    public static class HudRenderEvent extends Event<HudRenderCallback> {
        public void register(HudRenderCallback listener){
            onRenderHud.add(listener);
        }
    }
}
