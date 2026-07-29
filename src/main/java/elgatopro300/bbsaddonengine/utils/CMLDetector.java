package elgatopro300.bbsaddonengine.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class CMLDetector
{
    private static final Logger LOGGER = LoggerFactory.getLogger("BBS Addon Engine");
    private static Boolean cmlNativeDetected = null;

    public static boolean isCMLNativeDetected()
    {
        if (cmlNativeDetected == null)
        {
            try
            {
                Class<?> bbsModClass = Class.forName("mchorse.bbs_mod.BBSMod");
                Field field = bbsModClass.getField("IS_CML");
                cmlNativeDetected = field.getBoolean(null);
            }
            catch (Throwable ignored)
            {
                cmlNativeDetected = false;
            }

            if (Boolean.TRUE.equals(cmlNativeDetected))
            {
                LOGGER.info("BBS CML detected natively with built-in Addon Engine! Deactivating redundant BBS-ADDON-ENGINE mixins & event dispatches.");
            }
        }
        return Boolean.TRUE.equals(cmlNativeDetected);
    }
}
