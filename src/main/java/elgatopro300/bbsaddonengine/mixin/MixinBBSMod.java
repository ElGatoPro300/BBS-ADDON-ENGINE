package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngine;
import elgatopro300.bbsaddonengine.utils.CMLDetector;

import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.addons.BBSAddon;
import mchorse.bbs_mod.camera.clips.ClipFactoryData;
import mchorse.bbs_mod.events.BBSAddonMod;
import mchorse.bbs_mod.events.register.RegisterActionClipsEvent;
import mchorse.bbs_mod.events.register.RegisterActionConfigsEvent;
import mchorse.bbs_mod.events.register.RegisterCameraClipsEvent;
import mchorse.bbs_mod.events.register.RegisterEntityCaptureHandlersEvent;
import mchorse.bbs_mod.events.register.RegisterFormsEvent;
import mchorse.bbs_mod.events.register.RegisterKeyframeFactoriesEvent;
import mchorse.bbs_mod.events.register.RegisterMolangFunctionsEvent;
import mchorse.bbs_mod.events.register.RegisterParticleSimulationsEvent;
import mchorse.bbs_mod.events.register.RegisterSettingsEvent;
import mchorse.bbs_mod.events.register.RegisterSourcePacksEvent;
import mchorse.bbs_mod.forms.FormArchitect;
import mchorse.bbs_mod.math.molang.MolangParser;
import mchorse.bbs_mod.morphing.Morph;
import mchorse.bbs_mod.resources.packs.InternalAssetsSourcePack;
import mchorse.bbs_mod.utils.clips.Clip;
import mchorse.bbs_mod.utils.factory.MapFactory;
import mchorse.bbs_mod.utils.keyframes.factories.KeyframeFactories;

import net.fabricmc.loader.api.FabricLoader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BBSMod.class, remap = false)
public class MixinBBSMod
{
    @Shadow
    private static FormArchitect forms;

    @Shadow
    private static MapFactory<Clip, ClipFactoryData> factoryCameraClips;

    @Shadow
    private static MapFactory<Clip, ClipFactoryData> factoryActionClips;

    @Inject(method = "onInitialize", at = @At("HEAD"))
    private void injectLoadAddons(CallbackInfo ci)
    {
        if (CMLDetector.isCMLNativeDetected())
        {
            return;
        }

        FabricLoader.getInstance()
            .getEntrypointContainers("bbs-addon", BBSAddonMod.class)
            .forEach((container) ->
            {
                BBSMod.events.register(container.getEntrypoint());
            });
    }

    @Inject(method = "onInitialize", at = @At("RETURN"))
    private void injectPostEvents(CallbackInfo ci)
    {
        if (CMLDetector.isCMLNativeDetected())
        {
            return;
        }
        try
        {
            BBSMod.getProvider().register(new InternalAssetsSourcePack("bbs_addon_engine", "assets/bbs_addon_engine", BBSAddonEngine.class));
        }
        catch (Throwable ignored)
        {}

        BBSMod.events.post(new RegisterSourcePacksEvent(BBSMod.getProvider()));
        BBSMod.events.post(new RegisterMolangFunctionsEvent(BBSAddonEngine.customMolangFunctions));
        BBSMod.events.post(new RegisterFormsEvent(forms));
        BBSMod.events.post(new RegisterKeyframeFactoriesEvent(KeyframeFactories.FACTORIES));
        BBSMod.events.post(new RegisterEntityCaptureHandlersEvent());
        BBSMod.events.post(new RegisterCameraClipsEvent(factoryCameraClips));
        BBSMod.events.post(new RegisterActionClipsEvent(factoryActionClips));
        BBSMod.events.post(new RegisterSettingsEvent());
        BBSMod.events.post(new RegisterActionConfigsEvent());
        BBSMod.events.post(new RegisterParticleSimulationsEvent());
    }
}
