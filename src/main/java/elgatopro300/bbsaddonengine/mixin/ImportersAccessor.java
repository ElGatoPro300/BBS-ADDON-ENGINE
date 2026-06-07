package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.importers.Importers;
import mchorse.bbs_mod.importers.types.IImporter;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Importers.class)
public interface ImportersAccessor
{
    @Accessor("importers")
    public static List<IImporter> getImporters()
    {
        throw new AssertionError();
    }
}
