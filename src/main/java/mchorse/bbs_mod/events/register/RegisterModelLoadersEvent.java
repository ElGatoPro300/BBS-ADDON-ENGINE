package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.IModelManager;

import mchorse.bbs_mod.cubic.model.ModelManager;
import mchorse.bbs_mod.cubic.model.loaders.IModelLoader;

public class RegisterModelLoadersEvent
{
    private final ModelManager manager;

    public RegisterModelLoadersEvent(ModelManager manager)
    {
        this.manager = manager;
    }

    public void registerLoader(IModelLoader loader)
    {
        ((IModelManager) this.manager).registerLoader(loader);
    }

    public void registerRelodableSuffix(String suffix)
    {
        ((IModelManager) this.manager).registerRelodableSuffix(suffix);
    }
}
