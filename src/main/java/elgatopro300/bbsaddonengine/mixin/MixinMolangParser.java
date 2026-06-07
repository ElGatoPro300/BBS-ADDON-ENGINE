package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngine;
import mchorse.bbs_mod.math.functions.Function;
import mchorse.bbs_mod.math.molang.MolangParser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = MolangParser.class, remap = false)
public class MixinMolangParser
{
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci)
    {
        ((MolangParser) (Object) this).functions.putAll(BBSAddonEngine.customMolangFunctions);
    }
}
