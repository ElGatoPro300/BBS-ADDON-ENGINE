package elgatopro300.bbsaddonengine.utils;

import elgatopro300.bbsaddonengine.mixin.AccessorUIReplaysEditor;

import mchorse.bbs_mod.ui.utils.icons.Icon;

public class HelperUIReplaysEditor
{
    public static void registerColor(String id, int color)
    {
        AccessorUIReplaysEditor.getCOLORS().put(id, color);
    }

    public static void registerIcon(String id, Icon icon)
    {
        AccessorUIReplaysEditor.getICONS().put(id, icon);
    }
}
