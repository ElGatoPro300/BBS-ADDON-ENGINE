package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;

import mchorse.bbs_mod.ui.film.replays.overlays.UIReplaysOverlayPanel;

import java.util.function.Consumer;

public class RegisterReplayPanelEvent
{
    public void register(Consumer<UIReplaysOverlayPanel> consumer)
    {
        BBSAddonEngineClient.replaysOverlayPanelExtensions.add(consumer);
    }
}
