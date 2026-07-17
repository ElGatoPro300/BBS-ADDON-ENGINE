package mchorse.bbs_mod;

import elgatopro300.bbsaddonengine.mixin.BBSModAccessor;

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

/**
 * BBS utility class that provides easy access to BBS Mod's core components.
 */
public class BBS
{
    public static EventBus getEvents()
    {
        return BBSMod.events;
    }

    public static File getGameFolder()
    {
        return BBSModAccessor.getGameFolder();
    }

    public static File getAssetsFolder()
    {
        return BBSModAccessor.getAssetsFolder();
    }

    public static File getSettingsFolder()
    {
        return BBSModAccessor.getSettingsFolder();
    }

    public static File getWorldFolder()
    {
        return BBSModAccessor.getWorldFolder();
    }

    public static AssetProvider getProvider()
    {
        return BBSModAccessor.getProvider();
    }

    public static DynamicSourcePack getDynamicSourcePack()
    {
        return BBSModAccessor.getDynamicSourcePack();
    }

    public static ExternalAssetsSourcePack getOriginalSourcePack()
    {
        return BBSModAccessor.getOriginalSourcePack();
    }

    public static SettingsManager getSettings()
    {
        return BBSModAccessor.getSettings();
    }

    public static FormArchitect getForms()
    {
        return BBSModAccessor.getForms();
    }

    public static FilmManager getFilms()
    {
        return BBSModAccessor.getFilms();
    }

    public static ActionManager getActions()
    {
        return BBSModAccessor.getActions();
    }

    public static MapFactory<Clip, ClipFactoryData> getFactoryCameraClips()
    {
        return BBSModAccessor.getFactoryCameraClips();
    }

    public static MapFactory<Clip, ClipFactoryData> getFactoryActionClips()
    {
        return BBSModAccessor.getFactoryActionClips();
    }
}
