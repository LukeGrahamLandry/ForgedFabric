package ca.lukegrahamlandry.forgedfabric.mixins;

import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelPredicateProviderRegistry.class)
public interface ModelPredicateProviderRegistryInvoker {
    @Invoker("register")
    public static ModelPredicateProvider invokeRegister(Identifier id, ModelPredicateProvider provider) {
        throw new AssertionError();
    }
}
