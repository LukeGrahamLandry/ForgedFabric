package ca.lukegrahamlandry.forgedfabric.events;


import ca.lukegrahamlandry.forgedfabric.FabricOnForgeMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FabricOnForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event){
        ClientHelper.onTooltip.forEach((action) -> action.getTooltip(event.getItemStack(), event.getFlags(), event.getToolTip()));
    }

    @SubscribeEvent
    public static void onRenderHud(RenderGameOverlayEvent.Post event){
        ClientHelper.onRenderHud.forEach((action) -> action.onHudRender(event.getMatrixStack(), event.getPartialTicks()));
    }
}
