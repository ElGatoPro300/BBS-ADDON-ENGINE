package elgatopro300.bbsaddonengine.mixin.fs;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.film.replays.Replay;
import mchorse.bbs_mod.forms.forms.Form;
import mchorse.bbs_mod.ui.film.UIFilmPanel;
import mchorse.bbs_mod.ui.film.replays.UIReplayList;
import mchorse.bbs_mod.ui.utils.context.ContextMenuManager;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UIReplayList.class, remap = false)
public class MixinUIReplayListFS
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Consumer<List<Replay>> callback, Consumer<Form> formConsumer, UIFilmPanel panel, CallbackInfo ci)
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
