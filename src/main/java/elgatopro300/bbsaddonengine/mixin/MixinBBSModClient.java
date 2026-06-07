package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;
import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.BBSModClient;
import mchorse.bbs_mod.addons.AddonInfo;
import mchorse.bbs_mod.events.BBSAddonMod;
import mchorse.bbs_mod.events.register.*;
import mchorse.bbs_mod.ui.framework.elements.input.keyframes.shapes.KeyframeShapeRenderers;
import mchorse.bbs_mod.utils.interps.Interpolations;
import mchorse.bbs_mod.particles.ParticleScheme;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elgatopro300.bbsaddonengine.IBBSModClient;
import java.util.List;

@Mixin(value = BBSModClient.class, remap = false)
public class MixinBBSModClient implements IBBSModClient
{
    @Inject(method = "onInitializeClient", at = @At("RETURN"))
    private void injectOnInitializeClient(CallbackInfo ci)
    {
        FabricLoader.getInstance()
            .getEntrypointContainers("bbs-addon-client", BBSAddonMod.class)
            .forEach((container) ->
            {
                BBSMod.events.register(container.getEntrypoint());
            });

        BBSModClient.getL10n().register((lang) -> 
            java.util.Collections.singletonList(new mchorse.bbs_mod.resources.Link("bbs_addon_engine", "strings/" + lang + ".json"))
        );

        BBSMod.events.post(new RegisterL10nEvent(BBSModClient.getL10n()));

        BBSModClient.getL10n().reload();

        BBSMod.events.post(new RegisterModelLoadersEvent(BBSModClient.getModels()));
        BBSMod.events.post(new RegisterFormCategoriesEvent(BBSModClient.getFormCategories()));
        BBSMod.events.post(new RegisterImportersEvent());
        BBSMod.events.post(new RegisterParticleComponentsEvent(ParticleScheme.PARSER.components));
        BBSMod.events.post(new RegisterInterpolationsEvent(Interpolations.MAP));
        BBSMod.events.post(new RegisterFormsRenderersEvent());
        BBSMod.events.post(new RegisterFormEditorsEvent(UIFormEditorAccessor.getPanels()));
        BBSMod.events.post(new RegisterIconsEvent());
        BBSMod.events.post(new RegisterUIValueFactoriesEvent(UIValueMapAccessor.getFactories()));
        BBSMod.events.post(new RegisterUIKeyframeFactoriesEvent(UIKeyframeFactoryAccessor.getFactories()));
        BBSMod.events.post(new RegisterKeyframeShapesEvent(KeyframeShapeRenderers.SHAPES));
        BBSMod.events.post(new RegisterPropTransformEvent());
        BBSMod.events.post(new RegisterStencilMapEvent());
        BBSMod.events.post(new RegisterRayTracingEvent());
        BBSMod.events.post(new RegisterFilmPreviewEvent());
        BBSMod.events.post(new RegisterReplayListContextMenuEvent());
        BBSMod.events.post(new RegisterReplayPanelEvent());
        BBSMod.events.post(new RegisterClientSettingsEvent());
    }
}
