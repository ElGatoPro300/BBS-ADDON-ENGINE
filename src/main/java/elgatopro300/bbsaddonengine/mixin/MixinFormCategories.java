package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.forms.FormCategories;
import mchorse.bbs_mod.forms.sections.ExtraFormSection;
import mchorse.bbs_mod.forms.sections.FormSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Injects into FormCategories to capture the ExtraFormSection instance
 * and store it in the 'extraForms' field injected via ASM by BBSAddonEngineMixinPlugin.
 *
 * <p>BBS 1.7.7 does not have the 'extraForms' field or 'getExtraForms()' method.
 * These are added via ASM; this mixin populates the field after setup() runs.</p>
 */
@Mixin(value = FormCategories.class, remap = false)
public abstract class MixinFormCategories
{
    @Shadow
    private List<FormSection> sections;

    @Inject(method = "setup", at = @At("TAIL"))
    private void onSetupTail(CallbackInfo ci)
    {
        FormCategories self = (FormCategories) (Object) this;

        for (FormSection section : this.sections)
        {
            if (section instanceof ExtraFormSection)
            {
                // Assign to the ASM-injected public field 'extraForms'
                try
                {
                    self.getClass().getField("extraForms").set(self, section);
                }
                catch (Exception ignored) {}

                break;
            }
        }
    }
}
