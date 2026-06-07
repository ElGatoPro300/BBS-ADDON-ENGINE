package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.settings.ui.UIValueMap;
import mchorse.bbs_mod.settings.ui.UIValueMap.IUIValueFactory;
import mchorse.bbs_mod.settings.values.base.BaseValue;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = UIValueMap.class, remap = false)
public interface UIValueMapAccessor {
    @Accessor("factories")
    static Map<Class<? extends BaseValue>, IUIValueFactory<? extends BaseValue>> getFactories() {
        throw new AssertionError();
    }
}
