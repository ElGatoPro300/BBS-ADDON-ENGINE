package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.forms.sections.ExtraFormSection;

import org.spongepowered.asm.mixin.Mixin;

/**
 * Empty mixin targeting ExtraFormSection.
 *
 * <p>This mixin exists only to make the MixinPlugin's preApply() fire for ExtraFormSection,
 * so that ASM can inject the missing getExtraCategory() method that doesn't exist in BBS 1.7.7.</p>
 */
@Mixin(value = ExtraFormSection.class, remap = false)
public abstract class MixinExtraFormSection
{
}
