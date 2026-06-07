package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngine;
import mchorse.bbs_mod.forms.forms.Form;
import mchorse.bbs_mod.morphing.IEntityCaptureHandler;
import mchorse.bbs_mod.morphing.Morph;
import mchorse.bbs_mod.utils.RayTracing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = Morph.class, remap = false)
public class MixinMorph
{
    @Inject(method = "getMobForm", at = @At("HEAD"), cancellable = true)
    private static void injectGetMobForm(PlayerEntity player, CallbackInfoReturnable<Form> cir)
    {
        HitResult hitResult = RayTracing.rayTraceEntity(player, player.getWorld(), player.getEyePos(), player.getRotationVector(), 64);

        if (hitResult.getType() == HitResult.Type.ENTITY)
        {
            Entity target = ((EntityHitResult) hitResult).getEntity();

            for (IEntityCaptureHandler handler : BBSAddonEngine.handlers)
            {
                Form form = handler.capture(player, target);

                if (form != null)
                {
                    cir.setReturnValue(form);
                    return;
                }
            }
        }
    }
}
