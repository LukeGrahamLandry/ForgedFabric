package net.fabricmc.fabric.api.client.rendering.v1;

import ca.lukegrahamlandry.forgedfabric.events.ClientHelper;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public interface HudRenderCallback {
    Event<HudRenderCallback> EVENT = new ClientHelper.HudRenderEvent();

    void onHudRender(MatrixStack matrixStack, float tickDelta);
}
