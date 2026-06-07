package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngine;
import mchorse.bbs_mod.utils.IRayTracingHandler;
import mchorse.bbs_mod.utils.RayTracing;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = RayTracing.class, remap = false)
public class MixinRayTracing
{
    @Inject(method = "rayTrace(Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;D)Lnet/minecraft/util/hit/BlockHitResult;", at = @At("HEAD"), cancellable = true)
    private static void injectRayTrace(World world, Vec3d pos, Vec3d direction, double d, CallbackInfoReturnable<BlockHitResult> cir)
    {
        for (IRayTracingHandler handler : BBSAddonEngine.rayTracingHandlers)
        {
            BlockHitResult result = handler.rayTrace(world, pos, direction, d);

            if (result != null)
            {
                cir.setReturnValue(result);
                return;
            }
        }
    }

    @Inject(method = "rayTraceEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;D)Lnet/minecraft/util/hit/HitResult;", at = @At("HEAD"), cancellable = true)
    private static void injectRayTraceEntity(Entity entity, World world, Vec3d pos, Vec3d direction, double d, CallbackInfoReturnable<HitResult> cir)
    {
        for (IRayTracingHandler handler : BBSAddonEngine.rayTracingHandlers)
        {
            HitResult result = handler.rayTraceEntity(entity, world, pos, direction, d);

            if (result != null)
            {
                cir.setReturnValue(result);
                return;
            }
        }
    }
}
