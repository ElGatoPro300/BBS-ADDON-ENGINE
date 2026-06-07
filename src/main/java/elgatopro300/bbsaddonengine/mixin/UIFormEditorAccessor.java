package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.ui.forms.editors.UIFormEditor;
import mchorse.bbs_mod.ui.forms.editors.forms.UIForm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(UIFormEditor.class)
public interface UIFormEditorAccessor
{
    @Accessor("panels")
    public static Map<Class, Supplier<UIForm>> getPanels()
    {
        throw new AssertionError();
    }
}
