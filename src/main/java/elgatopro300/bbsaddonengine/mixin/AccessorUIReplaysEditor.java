package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.ui.film.replays.UIReplaysEditor;
import mchorse.bbs_mod.ui.utils.icons.Icon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Map;

@Mixin(value = UIReplaysEditor.class, remap = false)
public interface AccessorUIReplaysEditor
{
    @Accessor("COLORS")
    static Map<String, Integer> getCOLORS()
    {
        throw new AssertionError();
    }

    @Accessor("ICONS")
    static Map<String, Icon> getICONS()
    {
        throw new AssertionError();
    }
}
