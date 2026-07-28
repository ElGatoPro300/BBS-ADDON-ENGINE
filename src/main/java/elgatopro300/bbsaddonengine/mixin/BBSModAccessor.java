package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.BBSMod;
import mchorse.bbs_mod.actions.ActionManager;
import mchorse.bbs_mod.camera.clips.ClipFactoryData;
import mchorse.bbs_mod.events.EventBus;
import mchorse.bbs_mod.film.FilmManager;
import mchorse.bbs_mod.forms.FormArchitect;
import mchorse.bbs_mod.resources.AssetProvider;
import mchorse.bbs_mod.resources.packs.DynamicSourcePack;
import mchorse.bbs_mod.resources.packs.ExternalAssetsSourcePack;
import mchorse.bbs_mod.settings.SettingsManager;
import mchorse.bbs_mod.utils.clips.Clip;
import mchorse.bbs_mod.utils.factory.MapFactory;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = BBSMod.class, remap = false)
public interface BBSModAccessor
{
    @Accessor("gameFolder")
    static File getGameFolder()
    {
        throw new AssertionError();
    }

    @Accessor("assetsFolder")
    static File getAssetsFolder()
    {
        throw new AssertionError();
    }

    @Accessor("settingsFolder")
    static File getSettingsFolder()
    {
        throw new AssertionError();
    }

    @Accessor("worldFolder")
    static File getWorldFolder()
    {
        throw new AssertionError();
    }

    @Accessor("provider")
    static AssetProvider getProvider()
    {
        throw new AssertionError();
    }

    @Accessor("dynamicSourcePack")
    static DynamicSourcePack getDynamicSourcePack()
    {
        throw new AssertionError();
    }

    @Accessor("originalSourcePack")
    static ExternalAssetsSourcePack getOriginalSourcePack()
    {
        throw new AssertionError();
    }

    @Accessor("settings")
    static SettingsManager getSettings()
    {
        throw new AssertionError();
    }

    @Accessor("forms")
    static FormArchitect getForms()
    {
        throw new AssertionError();
    }

    @Accessor("films")
    static FilmManager getFilms()
    {
        throw new AssertionError();
    }

    @Accessor("actions")
    static ActionManager getActions()
    {
        throw new AssertionError();
    }

    @Accessor("factoryCameraClips")
    static MapFactory<Clip, ClipFactoryData> getFactoryCameraClips()
    {
        throw new AssertionError();
    }

    @Accessor("factoryActionClips")
    static MapFactory<Clip, ClipFactoryData> getFactoryActionClips()
    {
        throw new AssertionError();
    }
}
