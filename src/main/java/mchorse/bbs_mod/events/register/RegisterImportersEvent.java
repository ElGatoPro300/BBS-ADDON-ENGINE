package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.mixin.ImportersAccessor;

import mchorse.bbs_mod.importers.types.IImporter;

public class RegisterImportersEvent
{
    public void register(IImporter importer)
    {
        ImportersAccessor.getImporters().add(importer);
    }
}
