package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.BBSSettings;
import mchorse.bbs_mod.events.register.RegisterBBSSettingsEvent;
import mchorse.bbs_mod.settings.SettingsBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BBSSettings.class, remap = false)
public class MixinBBSSettings
{
    @Inject(method = "register", at = @At("RETURN"))
    private static void injectRegister(SettingsBuilder builder, CallbackInfo ci)
    {
        BBSMod.events.post(new RegisterBBSSettingsEvent(builder));
    }
}
