package elgatopro300.bbsaddonengine;

import mchorse.bbs_mod.addons.AddonInfo;

import java.util.List;

public interface IBBSModClient
{
    public static final List<AddonInfo> registeredAddons = BBSAddonEngineClient.registeredAddons;
}
