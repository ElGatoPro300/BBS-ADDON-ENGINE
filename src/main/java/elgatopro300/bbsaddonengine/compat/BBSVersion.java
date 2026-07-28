package elgatopro300.bbsaddonengine.compat;

import mchorse.bbs_mod.ui.film.replays.ReplayListEntry;

public final class BBSVersion
{
    public static final boolean IS_FS = detectFS();

    private static boolean detectFS()
    {
        try
        {
            Class.forName("ReplayListEntry");
            return true;
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
    }

    private BBSVersion() {}
}
