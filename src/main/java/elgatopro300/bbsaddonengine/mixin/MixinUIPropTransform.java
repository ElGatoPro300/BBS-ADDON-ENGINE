package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.ui.framework.elements.input.UIPropTransform;
import mchorse.bbs_mod.ui.utils.context.ContextMenuManager;

import java.util.List;
import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UIPropTransform.class, remap = false)
public class MixinUIPropTransform
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci)
    {
        UIPropTransform self = (UIPropTransform) (Object) this;
        self.context((menu) ->
        {
            for (BiConsumer<UIPropTransform, ContextMenuManager> consumer : BBSAddonEngineClient.contextMenuExtensions)
            {
                consumer.accept(self, menu);
            }
        });
    }
}
