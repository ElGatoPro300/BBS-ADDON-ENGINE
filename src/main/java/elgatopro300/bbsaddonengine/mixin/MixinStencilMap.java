package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;
import mchorse.bbs_mod.ui.framework.elements.utils.StencilMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

@Mixin(value = StencilMap.class, remap = false)
public class MixinStencilMap
{
    @Inject(method = "setup", at = @At("RETURN"))
    private void injectSetup(CallbackInfo ci)
    {
        for (Consumer<StencilMap> consumer : BBSAddonEngineClient.stencilMapExtensions)
        {
            consumer.accept((StencilMap) (Object) this);
        }
    }
}
