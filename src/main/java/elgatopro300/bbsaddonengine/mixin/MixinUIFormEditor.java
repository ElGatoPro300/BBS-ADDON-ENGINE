package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.ui.forms.editors.UIFormEditor;
import mchorse.bbs_mod.ui.forms.editors.utils.UIPickableFormRenderer;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = UIFormEditor.class, remap = false)
public class MixinUIFormEditor
{
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "(Lmchorse/bbs_mod/ui/forms/editors/UIFormEditor;)Lmchorse/bbs_mod/ui/forms/editors/utils/UIPickableFormRenderer;"))
    private UIPickableFormRenderer redirectRenderer(UIFormEditor editor)
    {
        return BBSAddonEngineClient.rendererFactory.apply(editor);
    }
}
