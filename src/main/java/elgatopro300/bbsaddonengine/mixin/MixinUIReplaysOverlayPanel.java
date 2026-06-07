package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;
import mchorse.bbs_mod.ui.film.replays.overlays.UIReplaysOverlayPanel;
import mchorse.bbs_mod.ui.film.UIFilmPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(value = UIReplaysOverlayPanel.class, remap = false)
public class MixinUIReplaysOverlayPanel
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(UIFilmPanel filmPanel, Consumer callback, CallbackInfo ci)
    {
        UIReplaysOverlayPanel self = (UIReplaysOverlayPanel) (Object) this;
        for (Consumer<UIReplaysOverlayPanel> consumer : BBSAddonEngineClient.replaysOverlayPanelExtensions)
        {
            consumer.accept(self);
        }
    }
}
