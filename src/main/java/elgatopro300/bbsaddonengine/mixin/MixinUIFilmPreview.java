package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.ui.film.UIFilmPreview;

import java.util.List;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UIFilmPreview.class, remap = false)
public class MixinUIFilmPreview
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci)
    {
        for (Consumer<UIFilmPreview> consumer : BBSAddonEngineClient.filmPreviewExtensions)
        {
            consumer.accept((UIFilmPreview) (Object) this);
        }
    }
}
