package elgatopro300.bbsaddonengine;

import mchorse.bbs_mod.addons.AddonInfo;
import mchorse.bbs_mod.ui.film.UIFilmPreview;
import mchorse.bbs_mod.ui.film.replays.UIReplayList;
import mchorse.bbs_mod.ui.film.replays.overlays.UIReplaysOverlayPanel;
import mchorse.bbs_mod.ui.forms.editors.UIFormEditor;
import mchorse.bbs_mod.ui.forms.editors.utils.UIPickableFormRenderer;
import mchorse.bbs_mod.ui.framework.elements.input.UIPropTransform;
import mchorse.bbs_mod.ui.framework.elements.utils.StencilMap;
import mchorse.bbs_mod.ui.utils.context.ContextMenuManager;
import net.fabricmc.api.ClientModInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BBSAddonEngineClient implements ClientModInitializer
{
    public static final List<AddonInfo> registeredAddons = new ArrayList<>();

    public static Function<UIFormEditor, UIPickableFormRenderer> rendererFactory = UIPickableFormRenderer::new;
    public static final List<BiConsumer<UIPropTransform, ContextMenuManager>> contextMenuExtensions = new ArrayList<>();
    public static final List<Consumer<StencilMap>> stencilMapExtensions = new ArrayList<>();
    public static final List<Consumer<UIFilmPreview>> filmPreviewExtensions = new ArrayList<>();
    public static final List<BiConsumer<UIReplayList, ContextMenuManager>> replayListExtensions = new ArrayList<>();
    public static final List<Consumer<UIReplaysOverlayPanel>> replaysOverlayPanelExtensions = new ArrayList<>();

    public static void registerAddon(AddonInfo info)
    {
        registeredAddons.add(info);
    }

    @Override
    public void onInitializeClient()
    {
    }
}
