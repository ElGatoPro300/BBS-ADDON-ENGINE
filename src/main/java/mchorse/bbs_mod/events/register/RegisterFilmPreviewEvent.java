package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.BBSAddonEngineClient;
import mchorse.bbs_mod.ui.film.UIFilmPreview;

import java.util.function.Consumer;

public class RegisterFilmPreviewEvent
{
    public void register(Consumer<UIFilmPreview> consumer)
    {
        BBSAddonEngineClient.filmPreviewExtensions.add(consumer);
    }
}
