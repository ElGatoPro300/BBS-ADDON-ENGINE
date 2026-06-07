package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.ui.framework.elements.input.keyframes.factories.UIKeyframeFactory;
import mchorse.bbs_mod.ui.framework.elements.input.keyframes.factories.UIKeyframeFactory.IUIKeyframeFactoryFactory;
import mchorse.bbs_mod.utils.keyframes.factories.IKeyframeFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = UIKeyframeFactory.class, remap = false)
public interface UIKeyframeFactoryAccessor
{
    @Accessor("FACTORIES")
    public static Map<IKeyframeFactory, IUIKeyframeFactoryFactory> getFactories()
    {
        throw new AssertionError();
    }
}
