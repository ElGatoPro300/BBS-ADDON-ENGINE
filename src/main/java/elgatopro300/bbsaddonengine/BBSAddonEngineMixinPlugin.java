package elgatopro300.bbsaddonengine;

import mchorse.bbs_mod.BBSModClient;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class BBSAddonEngineMixinPlugin implements IMixinConfigPlugin
{
    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) { return true; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() { return null; }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
        if (targetClassName.equals("BBSModClient"))
        {
            // Add public static void registerAddon(AddonInfo info) to BBSModClient
            MethodNode registerAddon = new MethodNode(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "registerAddon",
                "(Lmchorse/bbs_mod/addons/AddonInfo;)V",
                null,
                null
            );
            
            registerAddon.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
            registerAddon.instructions.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "elgatopro300/bbsaddonengine/BBSAddonEngineClient",
                "registerAddon",
                "(Lmchorse/bbs_mod/addons/AddonInfo;)V",
                false
            ));
            registerAddon.instructions.add(new InsnNode(Opcodes.RETURN));
            
            targetClass.methods.add(registerAddon);
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
