package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.cubic.model.ModelManager;
import mchorse.bbs_mod.cubic.model.loaders.IModelLoader;
import mchorse.bbs_mod.resources.Link;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elgatopro300.bbsaddonengine.IModelManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(value = ModelManager.class, remap = false)
public class MixinModelManager implements IModelManager
{
    @Shadow
    public static String MODELS_PREFIX;

    @Shadow
    public List<IModelLoader> loaders;

    @Unique
    private final Set<String> relodableSuffixes = new HashSet<>();

    @Override
    public void registerLoader(IModelLoader loader)
    {
        if (loader != null)
        {
            this.loaders.add(loader);
        }
    }

    @Override
    public void registerRelodableSuffix(String suffix)
    {
        if (suffix != null && !suffix.isEmpty())
        {
            this.relodableSuffixes.add(suffix);
        }
    }

    @Inject(method = "isRelodable", at = @At("HEAD"), cancellable = true)
    private void injectIsRelodable(Link link, CallbackInfoReturnable<Boolean> cir)
    {
        if (!link.path.startsWith(MODELS_PREFIX))
        {
            cir.setReturnValue(false);
            return;
        }

        if (link.path.contains("/animations/") || link.path.contains("/shapes/"))
        {
            cir.setReturnValue(false);
            return;
        }

        for (String suffix : this.relodableSuffixes)
        {
            if (link.path.endsWith(suffix))
            {
                cir.setReturnValue(true);
                return;
            }
        }
    }
}
