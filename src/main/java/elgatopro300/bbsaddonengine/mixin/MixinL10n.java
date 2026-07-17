package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.events.L10nReloadEvent;
import mchorse.bbs_mod.l10n.L10n;
import mchorse.bbs_mod.resources.AssetProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = L10n.class, remap = false)
public class MixinL10n
{
    @Inject(method = "reload(Ljava/lang/String;Lmchorse/bbs_mod/resources/AssetProvider;)V", at = @At("RETURN"))
    private void injectReload(String language, AssetProvider provider, CallbackInfo ci)
    {
        BBSMod.events.post(new L10nReloadEvent((L10n) (Object) this));
    }
}
