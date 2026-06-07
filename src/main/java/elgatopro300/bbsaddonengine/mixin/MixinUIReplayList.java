package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.ui.film.UIFilmPanel;
import mchorse.bbs_mod.ui.film.replays.UIReplayList;
import mchorse.bbs_mod.ui.film.replays.overlays.UIReplaysOverlayPanel;
import mchorse.bbs_mod.ui.utils.context.ContextMenuManager;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UIReplayList.class, remap = false)
public class MixinUIReplayList
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Consumer callback, UIReplaysOverlayPanel overlay, UIFilmPanel panel, CallbackInfo ci)
    {
        UIReplayList self = (UIReplayList) (Object) this;
        self.context((menu) ->
        {
            for (BiConsumer<UIReplayList, ContextMenuManager> consumer : BBSAddonEngineClient.replayListExtensions)
            {
                consumer.accept(self, menu);
            }
        });
    }
}
