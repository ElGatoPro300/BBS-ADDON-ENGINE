package elgatopro300.bbsaddonengine;

import mchorse.bbs_mod.cubic.model.loaders.IModelLoader;

public interface IModelManager
{
    public void registerLoader(IModelLoader loader);
    public void registerRelodableSuffix(String suffix);
}
