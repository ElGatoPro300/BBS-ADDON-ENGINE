package elgatopro300.bbsaddonengine.mixin;

import mchorse.bbs_mod.l10n.L10n;
import mchorse.bbs_mod.ui.addons.UIAddonsOverlayPanel;
import mchorse.bbs_mod.ui.dashboard.UIDashboard;
import mchorse.bbs_mod.ui.framework.elements.buttons.UIIcon;
import mchorse.bbs_mod.ui.framework.elements.overlay.UIOverlay;
import mchorse.bbs_mod.ui.utils.icons.Icons;
import mchorse.bbs_mod.utils.Direction;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UIDashboard.class, remap = false)
public class MixinUIDashboard
{
    public UIAddonsOverlayPanel addonsPanel;
    public UIIcon addons;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci)
    {
        UIDashboard dashboard = (UIDashboard) (Object) this;
        this.addonsPanel = new UIAddonsOverlayPanel();
        this.addons = new UIIcon(Icons.SERVER, (b) ->
        {
            UIOverlay.addOverlayRight(dashboard.context, this.addonsPanel, 240);
        });
        this.addons.tooltip(L10n.lang("bbs.ui.addons.key"), Direction.TOP);
        dashboard.getPanels().pinned.add(this.addons);
    }
}
