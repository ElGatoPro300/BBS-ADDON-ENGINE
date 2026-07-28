package elgatopro300.bbsaddonengine;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import elgatopro300.bbsaddonengine.compat.BBSVersion;

public class BBSAddonEngineMixinPlugin implements IMixinConfigPlugin
{
    private static final String FS_PACKAGE = "elgatopro300.bbsaddonengine.mixin.fs.";
    private static final Set<String> BLOCKED_ON_FS = Set.of(
        "elgatopro300.bbsaddonengine.mixin.MixinUIReplayList"
    );

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        if (mixinClassName.startsWith(FS_PACKAGE))
        {
            return BBSVersion.IS_FS;
        }
        return !BBSVersion.IS_FS || !BLOCKED_ON_FS.contains(mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() { return null; }

    /** Check if a method already exists in a class to avoid duplicates. */
    private boolean hasMethod(ClassNode classNode, String name, String desc)
    {
        for (MethodNode m : classNode.methods)
        {
            if (m.name.equals(name) && m.desc.equals(desc)) return true;
        }
        return false;
    }

    /** Check if a field already exists in a class to avoid duplicates. */
    private boolean hasField(ClassNode classNode, String name, String desc)
    {
        for (FieldNode f : classNode.fields)
        {
            if (f.name.equals(name) && f.desc.equals(desc)) return true;
        }
        return false;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
        if (targetClassName.endsWith("BBSModClient"))
        {
            // Add: public static void registerAddon(AddonInfo info)
            if (!hasMethod(targetClass, "registerAddon", "(Lmchorse/bbs_mod/addons/AddonInfo;)V"))
            {
                MethodNode m = new MethodNode(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                    "registerAddon",
                    "(Lmchorse/bbs_mod/addons/AddonInfo;)V",
                    null, null
                );
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                m.instructions.add(new MethodInsnNode(
                    Opcodes.INVOKESTATIC,
                    "elgatopro300/bbsaddonengine/BBSAddonEngineClient",
                    "registerAddon",
                    "(Lmchorse/bbs_mod/addons/AddonInfo;)V",
                    false
                ));
                m.instructions.add(new InsnNode(Opcodes.RETURN));
                targetClass.methods.add(m);
            }
        }
        else if (targetClassName.endsWith("UIReplaysEditor"))
        {
            // Add: public static void registerColor(String id, int color)
            if (!hasMethod(targetClass, "registerColor", "(Ljava/lang/String;I)V"))
            {
                MethodNode m = new MethodNode(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                    "registerColor",
                    "(Ljava/lang/String;I)V",
                    null, null
                );
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                m.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
                m.instructions.add(new MethodInsnNode(
                    Opcodes.INVOKESTATIC,
                    "elgatopro300/bbsaddonengine/utils/HelperUIReplaysEditor",
                    "registerColor",
                    "(Ljava/lang/String;I)V",
                    false
                ));
                m.instructions.add(new InsnNode(Opcodes.RETURN));
                targetClass.methods.add(m);
            }

            // Add: public static void registerIcon(String id, Icon icon)
            if (!hasMethod(targetClass, "registerIcon", "(Ljava/lang/String;Lmchorse/bbs_mod/ui/utils/icons/Icon;)V"))
            {
                MethodNode m = new MethodNode(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                    "registerIcon",
                    "(Ljava/lang/String;Lmchorse/bbs_mod/ui/utils/icons/Icon;)V",
                    null, null
                );
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
                m.instructions.add(new MethodInsnNode(
                    Opcodes.INVOKESTATIC,
                    "elgatopro300/bbsaddonengine/utils/HelperUIReplaysEditor",
                    "registerIcon",
                    "(Ljava/lang/String;Lmchorse/bbs_mod/ui/utils/icons/Icon;)V",
                    false
                ));
                m.instructions.add(new InsnNode(Opcodes.RETURN));
                targetClass.methods.add(m);
            }
        }
        else if (targetClassName.equals("mchorse.bbs_mod.forms.FormCategories"))
        {
            // BBS 1.7.7 jar does not have the 'extraForms' field nor getExtraForms().
            // They were added in a later version of BBS Base.
            // bbs-aaaddon calls FormCategories.getExtraForms() so we must inject it.

            // Add field: public ExtraFormSection extraForms
            if (!hasField(targetClass, "extraForms", "Lmchorse/bbs_mod/forms/sections/ExtraFormSection;"))
            {
                targetClass.fields.add(new FieldNode(
                    Opcodes.ACC_PUBLIC,
                    "extraForms",
                    "Lmchorse/bbs_mod/forms/sections/ExtraFormSection;",
                    null, null
                ));
            }

            // Add method: public ExtraFormSection getExtraForms()
            if (!hasMethod(targetClass, "getExtraForms", "()Lmchorse/bbs_mod/forms/sections/ExtraFormSection;"))
            {
                MethodNode m = new MethodNode(
                    Opcodes.ACC_PUBLIC,
                    "getExtraForms",
                    "()Lmchorse/bbs_mod/forms/sections/ExtraFormSection;",
                    null, null
                );
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                m.instructions.add(new FieldInsnNode(
                    Opcodes.GETFIELD,
                    "mchorse/bbs_mod/forms/FormCategories",
                    "extraForms",
                    "Lmchorse/bbs_mod/forms/sections/ExtraFormSection;"
                ));
                m.instructions.add(new InsnNode(Opcodes.ARETURN));
                targetClass.methods.add(m);
            }
        }
        else if (targetClassName.equals("mchorse.bbs_mod.forms.sections.ExtraFormSection"))
        {
            // BBS 1.7.7 jar does not have getExtraCategory().
            // bbs-aaaddon calls ExtraFormSection.getExtraCategory() so we inject it.
            // The field 'extra' (FormCategory) does exist in the 1.7.7 jar.

            if (!hasMethod(targetClass, "getExtraCategory", "()Lmchorse/bbs_mod/forms/categories/FormCategory;"))
            {
                MethodNode m = new MethodNode(
                    Opcodes.ACC_PUBLIC,
                    "getExtraCategory",
                    "()Lmchorse/bbs_mod/forms/categories/FormCategory;",
                    null, null
                );
                m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                m.instructions.add(new FieldInsnNode(
                    Opcodes.GETFIELD,
                    "mchorse/bbs_mod/forms/sections/ExtraFormSection",
                    "extra",
                    "Lmchorse/bbs_mod/forms/categories/FormCategory;"
                ));
                m.instructions.add(new InsnNode(Opcodes.ARETURN));
                targetClass.methods.add(m);
            }
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
