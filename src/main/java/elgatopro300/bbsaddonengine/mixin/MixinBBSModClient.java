package elgatopro300.bbsaddonengine.mixin;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;
import elgatopro300.bbsaddonengine.IBBSModClient;

import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.BBSModClient;
import mchorse.bbs_mod.addons.AddonInfo;
import mchorse.bbs_mod.events.BBSAddonMod;
import mchorse.bbs_mod.events.register.*;
import mchorse.bbs_mod.events.register.RegisterClipInteractionEvent;
import mchorse.bbs_mod.events.register.RegisterDockLayoutEvent;
import mchorse.bbs_mod.events.register.RegisterFilmControllerInteractionEvent;
import mchorse.bbs_mod.events.register.RegisterFormBlendEvent;
import mchorse.bbs_mod.events.register.RegisterFormEditorSectionEvent;
import mchorse.bbs_mod.events.register.RegisterFormRenderPhaseEvent;
import mchorse.bbs_mod.events.register.RegisterParticleSchemeUIEvent;
import mchorse.bbs_mod.events.register.RegisterSettingsUISectionEvent;
import mchorse.bbs_mod.events.register.RegisterUIThemeEvent;
import mchorse.bbs_mod.particles.ParticleScheme;
import mchorse.bbs_mod.resources.Link;
import mchorse.bbs_mod.ui.framework.elements.input.keyframes.shapes.KeyframeShapeRenderers;
import mchorse.bbs_mod.utils.interps.Interpolations;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

        /* Register addons from FabricLoader */
        FabricLoader.getInstance()
            .getEntrypointContainers("bbs-addon", BBSAddonMod.class)
            .forEach((container) ->
            {
                ModMetadata meta = container.getProvider().getMetadata();
                String id = meta.getId();
                String name = meta.getName();
                String version = meta.getVersion().getFriendlyString();
                String description = meta.getDescription();
                List<String> authors = meta.getAuthors().stream().map(Person::getName).collect(Collectors.toList());
                
                Link icon = null;
                Optional<String> iconPath = meta.getIconPath(64);
                if (iconPath.isPresent())
                {
                    String path = iconPath.get();
                    if (path.startsWith("assets/"))
                    {
                        String relative = path.substring("assets/".length());
                        icon = new Link("mod_icons", relative);
                    }
                }
                
                ContactInformation contact = meta.getContact();
                String website = contact.get("homepage").orElse("");
                String issues = contact.get("issues").orElse("");
                String source = contact.get("sources").orElse("");

                BBSAddonEngineClient.registerAddon(new AddonInfo(id, name, version, description, authors, icon, website, issues, source));
            });

        BBSModClient.getL10n().register((lang) -> 
            Collections.singletonList(new Link("bbs_addon_engine", "strings/" + lang + ".json"))
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
        BBSMod.events.post(new RegisterUIThemeEvent());
        BBSMod.events.post(new RegisterFormEditorSectionEvent());
        BBSMod.events.post(new RegisterFormRenderPhaseEvent());
        BBSMod.events.post(new RegisterFormBlendEvent());
        BBSMod.events.post(new RegisterClipInteractionEvent());
        BBSMod.events.post(new RegisterDockLayoutEvent(BBSModClient.getDashboard()));
        BBSMod.events.post(new RegisterParticleSchemeUIEvent());
        BBSMod.events.post(new RegisterFilmControllerInteractionEvent());
        BBSMod.events.post(new RegisterSettingsUISectionEvent());
    }
}
