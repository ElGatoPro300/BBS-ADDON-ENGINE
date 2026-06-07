package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.BBSAddonEngine;
import mchorse.bbs_mod.utils.IRayTracingHandler;

public class RegisterRayTracingEvent
{
    public void register(IRayTracingHandler handler)
    {
        BBSAddonEngine.rayTracingHandlers.add(handler);
    }
}
