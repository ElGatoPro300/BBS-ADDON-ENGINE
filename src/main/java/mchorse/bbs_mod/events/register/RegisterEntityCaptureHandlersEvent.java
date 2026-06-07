package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.BBSAddonEngine;

import mchorse.bbs_mod.morphing.IEntityCaptureHandler;

public class RegisterEntityCaptureHandlersEvent
{
    public void register(IEntityCaptureHandler handler)
    {
        BBSAddonEngine.handlers.add(handler);
    }
}
