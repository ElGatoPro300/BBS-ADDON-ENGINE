package mchorse.bbs_mod.events.register;

import elgatopro300.bbsaddonengine.mixin.BBSModAccessor;

import mchorse.bbs_mod.settings.Settings;
import mchorse.bbs_mod.settings.SettingsBuilder;
import mchorse.bbs_mod.ui.utils.icons.Icon;

import java.io.File;
import java.util.function.Consumer;

public class RegisterSettingsEvent
{
    public void register(Icon icon, String id, Consumer<SettingsBuilder> consumer)
    {
        File destination = new File(BBSModAccessor.getSettingsFolder(), id + ".json");
        SettingsBuilder builder = new SettingsBuilder(icon, id, destination);
        Settings settings = builder.getConfig();

        consumer.accept(builder);

        BBSModAccessor.getSettings().modules.put(settings.getId(), settings);
        BBSModAccessor.getSettings().load(settings, settings.file);
    }
}
