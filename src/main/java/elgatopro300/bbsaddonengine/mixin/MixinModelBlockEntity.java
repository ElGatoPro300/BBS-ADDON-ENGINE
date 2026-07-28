package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.blocks.entities.ModelBlockEntity;
import mchorse.bbs_mod.events.ModelBlockEntityUpdateCallback;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModelBlockEntity.class, remap = false)
public class MixinModelBlockEntity
{
    @Inject(method = "tick", at = @At("HEAD"))
    private void injectTick(World world, BlockPos pos, BlockState state, CallbackInfo ci)
    {
        ModelBlockEntityUpdateCallback.EVENT.invoker().update((ModelBlockEntity) (Object) this);
    }
}
