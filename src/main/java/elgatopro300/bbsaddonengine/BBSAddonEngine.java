package elgatopro300.bbsaddonengine;

import mchorse.bbs_mod.math.functions.Function;
import mchorse.bbs_mod.morphing.IEntityCaptureHandler;
import mchorse.bbs_mod.utils.IRayTracingHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BBSAddonEngine implements ModInitializer
{
    public static final Logger LOGGER = LoggerFactory.getLogger("BBS Addon Engine");

    public static final List<IEntityCaptureHandler> handlers = new ArrayList<>();
    public static final Map<String, Class<? extends Function>> customMolangFunctions = new HashMap<>();
    public static final List<IRayTracingHandler> rayTracingHandlers = new ArrayList<>();

    @Override
    public void onInitialize()
    {
        LOGGER.info("BBS Addon Engine common initialization");
    }
}
